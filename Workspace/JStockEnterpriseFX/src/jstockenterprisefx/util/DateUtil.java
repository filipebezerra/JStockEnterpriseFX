package jstockenterprisefx.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Fun��es auxiliares para lidar com datas.
 * 
 * @author Marco Jakob
 */
public final class DateUtil {
	/** O padr�o usado para convers�o. Mude como quiser. */
	private static final String DATE_PATTERN = "dd/MM/yyyy";

	/** O formatador de data. */
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
			.ofPattern(DATE_PATTERN);

	/**
	 * Retorna os dados como String formatado. O {@link DateUtil#DATE_PATTERN}
	 * (padr�o de data) que � utilizado.
	 * 
	 * @param date
	 *            A data a ser retornada como String
	 * @return String formadado
	 */
	public static String format(final LocalDate date) {
		if (date == null)
			return null;
		return DATE_FORMATTER.format(date);
	}

	/**
	 * Converte um String no formato definido {@link DateUtil#DATE_PATTERN} para
	 * um objeto {@link LocalDate}.
	 * 
	 * Retorna null se o String n�o puder se convertido.
	 * 
	 * @param dateString
	 *            a data como String
	 * @return o objeto data ou null se n�o puder ser convertido
	 */
	public static LocalDate parse(final String dateString) {
		try {
			return DATE_FORMATTER.parse(dateString, LocalDate::from);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	/**
	 * Checa se o String � uma data v�lida.
	 * 
	 * @param dateString
	 *            A data como String
	 * @return true se o String � uma data v�lida
	 */
	public static boolean validDate(final String dateString) {
		// Tenta converter o String.
		return DateUtil.parse(dateString) != null;
	}
}
