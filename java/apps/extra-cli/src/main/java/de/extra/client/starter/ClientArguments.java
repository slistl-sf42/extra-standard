package de.extra.client.starter;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.springframework.util.StringUtils;

import de.extra.client.exit.SystemExiter;

/**
 * Extra Client Argumentauswertung.
 *
 * <p>
 * Argumente werden beim Start des Extra-Clients übergeben (zum Beispiel über
 * die Kommandozeile) und steuern und konfigurieren die Verarbeitung des
 * Clients. Gleichnamige Argumente haben in der Regel Vorrang vor
 * Konfigurationsparametern. Es gibt pro Client-Lauf nur eine Instanz, die
 * einmalig zum Start des Clients gefüllt wird.
 * </p>
 *
 * <p>
 * Kapselt die technische Realisierung der Kommandozeilenauswertung.
 * </p>
 * @author DPRS
 * @version $Id$
 */
public class ClientArguments {

	public static final String OPTION_NAME_HELP = "help";
	public static final String OPTION_NAME_HELP_SHORTCUT = "h";

	public static final String OPTION_NAME_CONFIGDIR = "configDirectory";
	public static final String OPTION_NAME_CONFIGDIR_SHORTCUT = "c";

	public static final String OPTION_NAME_LOGDIR = "logDirectory";
	public static final String OPTION_NAME_LOGDIR_SHORTCUT = "l";

	private static final Option OPT_HELP = new Option(OPTION_NAME_HELP_SHORTCUT, OPTION_NAME_HELP, false, "Hilfe anzeigen");

	private static final Option OPT_CONFIGDIRECTORY = new Option(OPTION_NAME_CONFIGDIR_SHORTCUT, OPTION_NAME_CONFIGDIR, true, "Konfigurationsverzeichnis");

	private static final Option OPT_LOGDIRECTORY = new Option(OPTION_NAME_LOGDIR_SHORTCUT, OPTION_NAME_LOGDIR, true, "Logverzeichnis");

	public static final Options OPTIONS;

	private final SystemExiter exiter;

	static {
		OPTIONS = new Options();
		OPTIONS.addOption(OPT_HELP);
		OPTIONS.addOption(OPT_CONFIGDIRECTORY);
		OPTIONS.addOption(OPT_LOGDIRECTORY);
	}

	/**
	 * Kommandozeile
	 */
	private final String[] args;

	/**
	 * Wert aus dem ermitteltem Kommandozeilenparameter {@link #OPTION_NAME_CONFIGDIR}.
	 */
	private File configDirectory = null;

	/**
	 * Wert aus dem ermitteltem Kommandozeilenparameter {@link #OPTION_NAME_LOGDIR}.
	 */
	private File logDirectory = null;

	/**
	 * Gibt an ob ein Hilfetext ausgegeben werden soll Parameter #OPTION_NAME_HELP.
	 */
	private Boolean showHelp = null;

	public ClientArguments(final String[] args, final SystemExiter exiter) {
		this.args = args;
		this.exiter = exiter;
	}

	/**
	 * Zerlegt die Kommandozeile, setzt erwartete Optionen, behandelt ggf.
	 * Fehler.
	 */
	public void parseArgs() {
		CommandLineParser parser = new PosixParser();

		CommandLine commandLine = null;
		try {
			commandLine = parser.parse(OPTIONS, this.args);
		} catch (ParseException e) {
			printHelpText(e);
			exiter.exit(ReturnCode.TECHNICAL);
		}

		this.showHelp = Boolean.valueOf(commandLine.hasOption(OPTION_NAME_HELP));

		if (commandLine.hasOption(OPTION_NAME_LOGDIR)) {
			String optionValue = commandLine.getOptionValue(OPTION_NAME_LOGDIR) != null ? commandLine.getOptionValue(OPTION_NAME_LOGDIR).trim() : null;
			if (!StringUtils.hasText(optionValue)) {
				throw new IllegalArgumentException("Logverzeichnis muss angegeben werden.");
			}
			logDirectory = new File(optionValue);
			checkDirectory(optionValue, logDirectory);
		} else if (Boolean.FALSE.equals(showHelp)) {
			throw new IllegalArgumentException("Bitte Parameter angeben.");
		}

		if (commandLine.hasOption(OPTION_NAME_CONFIGDIR)) {
			String optionValue = commandLine.getOptionValue(OPTION_NAME_CONFIGDIR) != null ? commandLine.getOptionValue(OPTION_NAME_CONFIGDIR).trim() : null;
			if (!StringUtils.hasText(optionValue)) {
				throw new IllegalArgumentException("Konfigurationsverzeichnis muss angegeben werden.");
			}
			configDirectory = new File(optionValue);
			checkDirectory(optionValue, configDirectory);
		} else if (Boolean.FALSE.equals(showHelp)) {
			throw new IllegalArgumentException("Bitte Parameter angeben.");
		}

		if (showHelp == null && configDirectory == null) {
			throw new IllegalArgumentException("Bitte Parameter angeben.");
		}
	}

	/**
	 * @param optionValue
	 */
	private void checkDirectory(final String optionValue, final File directory) {
		if (!directory.exists()) {
			throw new IllegalArgumentException(String.format("Verzeichnis existiert nicht: %s", directory));
		}
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException(String.format("Verzeichnis ist kein Verzeichnis: %s", directory));
		}
		if (!directory.canRead()) {
			throw new IllegalArgumentException(String.format("Verzeichnis nicht zugreifbar: %s", directory));
		}
	}

	public void printHelpText(final Exception e) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("extraClient", OPTIONS);
		if (e != null) {
			System.out.println("\nFehler: " + e.getMessage());
		}
	}

	public boolean isShowHelp() {
		return showHelp;
	}

	public File getConfigDirectory() {
		return configDirectory;
	}

	public File getLogDirectory() {
		return logDirectory;
	}
}