package jigndu;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

import freemarker.core.Environment;
import freemarker.core.InvalidFormatParametersException;
import freemarker.core.TemplateNumberFormat;
import freemarker.core.TemplateNumberFormatFactory;
import freemarker.log.Logger;

public class MyJavaTemplateNumberFormatFactory extends TemplateNumberFormatFactory
{
	static final JavaTemplateNumberFormatFactory INSTANCE = new JavaTemplateNumberFormatFactory();

	private static final Logger LOG = Logger.getLogger("freemarker.runtime");

	private static final ConcurrentHashMap<CacheKey, NumberFormat> GLOBAL_FORMAT_CACHE = new ConcurrentHashMap<CacheKey, NumberFormat>();

	private static final int LEAK_ALERT_NUMBER_FORMAT_CACHE_SIZE = 1024;

	private JavaTemplateNumberFormatFactory() {
	        // Not meant to be instantiated
	    }

	@Override
	public TemplateNumberFormat get(String params, Locale locale, Environment env) throws InvalidFormatParametersException
	{
		CacheKey cacheKey = new CacheKey(params, locale);
		NumberFormat jFormat = GLOBAL_FORMAT_CACHE.get(cacheKey);
		if (jFormat == null)
		{
			if ("number".equals(params))
			{
				jFormat = NumberFormat.getNumberInstance(locale);
			}
			else if ("currency".equals(params))
			{
				jFormat = NumberFormat.getCurrencyInstance(locale);
			}
			else if ("percent".equals(params))
			{
				jFormat = NumberFormat.getPercentInstance(locale);
			}
			else if ("computer".equals(params))
			{
				jFormat = env.getCNumberFormat();
			}
			else
			{
				try
				{
					jFormat = ExtendedDecimalFormatParser.parse(params, locale);
				}
				catch (ParseException e)
				{
					String msg = e.getMessage();
					throw new InvalidFormatParametersException(msg != null ? msg : "Invalid DecimalFormat pattern", e);
				}
			}

			if (GLOBAL_FORMAT_CACHE.size() >= LEAK_ALERT_NUMBER_FORMAT_CACHE_SIZE)
			{
				boolean triggered = false;
				synchronized (JavaTemplateNumberFormatFactory.class)
				{
					if (GLOBAL_FORMAT_CACHE.size() >= LEAK_ALERT_NUMBER_FORMAT_CACHE_SIZE)
					{
						triggered = true;
						GLOBAL_FORMAT_CACHE.clear();
					}
				}
				if (triggered)
				{
					LOG.warn("Global Java NumberFormat cache has exceeded " + LEAK_ALERT_NUMBER_FORMAT_CACHE_SIZE + " entries => cache flushed. "
							+ "Typical cause: Some template generates high variety of format pattern strings.");
				}
			}

			NumberFormat prevJFormat = GLOBAL_FORMAT_CACHE.putIfAbsent(cacheKey, jFormat);
			if (prevJFormat != null)
			{
				jFormat = prevJFormat;
			}
		} // if cache miss

		// JFormat-s aren't thread-safe; must clone it
		jFormat = (NumberFormat) jFormat.clone();

		return new JavaTemplateNumberFormat(jFormat, params);
	}

	private static final class CacheKey
	{
		private final String pattern;

		private final Locale locale;

		CacheKey(String pattern, Locale locale)
		{
			this.pattern = pattern;
			this.locale = locale;
		}

		@Override
		public boolean equals(Object o)
		{
			if (o instanceof CacheKey)
			{
				CacheKey fk = (CacheKey) o;
				return fk.pattern.equals(pattern) && fk.locale.equals(locale);
			}
			return false;
		}

		@Override
		public int hashCode()
		{
			return pattern.hashCode() ^ locale.hashCode();
		}
	}
}
