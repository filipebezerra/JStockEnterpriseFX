package jstockenterprisefx.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Funções auxiliares para lidar com datas.
 * 
 * @author Marco Jakob
 */
public final class DateUtils {
	/** O padrão usado para conversão. Mude como quiser. */
	private static final String DATE_PATTERN = "dd/MM/yyyy";
	
	private static final String DATE_TIME_PATTERN = DATE_PATTERN.concat(" HH:mm:ss");

	/** O formatador de data. */
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
			.ofPattern(DATE_PATTERN);
	
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
			.ofPattern(DATE_TIME_PATTERN);

	/**
	 * Retorna os dados como String formatado. O {@link DateUtils#DATE_PATTERN}
	 * (padrão de data) que é utilizado.
	 * 
	 * @param date
	 *            A data a ser retornada como String
	 * @return String data formatada
	 */
	public static String format(final LocalDate date) {
		if (date == null)
			return null;
		return DATE_FORMATTER.format(date);
	}

	/**
	 * Retorna os dados como String formatado. O {@link DateUtils#DATE_TIME_PATTERN}
	 * (padrão de data e hora) que é utilizado.
	 * 
	 * @param date
	 *            A data e hora a ser retornada como String
	 * @return String data e hora formatada
	 */
	public static String format(final LocalDateTime dateTime) {
		if (dateTime == null)
			return null;
		return DATE_TIME_FORMATTER.format(dateTime);
	}
	
	/**
	 * Converte um String no formato definido {@link DateUtils#DATE_PATTERN} para
	 * um objeto {@link LocalDate}.
	 * 
	 * Retorna null se o String não puder se convertido.
	 * 
	 * @param dateString
	 *            a data como String
	 * @return o objeto data ou null se não puder ser convertido
	 */
	public static LocalDate parseDate(final String dateString) {
		try {
			return DATE_FORMATTER.parse(dateString, LocalDate::from);
		} catch (DateTimeParseException e) {
			return null;
		}
	}
	
	/**
	 * Converte um String no formato definido {@link DateUtils#DATE_TIME_PATTERN} para
	 * um objeto {@link LocalDateTime}.
	 * 
	 * Retorna null se o String não puder se convertido.
	 * 
	 * @param dateTimeString
	 *            a data e hora como String
	 * @return o objeto data ou null se não puder ser convertido
	 */
	public static LocalDateTime parseDateTime(final String dateTimeString) {
		try {
			return DATE_TIME_FORMATTER.parse(dateTimeString, LocalDateTime::from);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	/**
	 * Checa se o String é uma data válida.
	 * 
	 * @param dateString
	 *            A data como String
	 * @return true se o String é uma data válida
	 */
	public static boolean validDate(final String dateString) {
		return DateUtils.parseDate(dateString) != null;
	}
}
