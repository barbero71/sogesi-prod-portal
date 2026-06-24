package it.sogesispa.prod.web.scheduler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;

import it.sogesispa.prod.web.services.PianificazioneAttivitaService;

public class Scheduler {
	private static final Logger logger = Logger.getLogger(Scheduler.class);

	public final static int PERIODICITA_GIORNALIERA = 1;
	public final static int PERIODICITA_SETTIMANALE = 2;
	public final static int PERIODICITA_MENSILE = 5;
	public final static int PERIODICITA_BIMESTRALE = 6;
	public final static int PERIODICITA_TRIMESTRALE = 7;
	public final static int PERIODICITA_SEMESTRALE = 8;
	public final static int PERIODICITA_ANNUALE = 9;
	public final static int PERIODICITA_QUADRIMESTRALE = 10;
//	public final static int PERIODICITA_OGNI2GG = 2;
//	public final static int PERIODICITA_QUINDICINALE = 4;
	 

	private String[] excludedDate;// {"25-12","24-12","01-01","06-01","15-08","01-11","01-05","02-06"};

	public String[] getExcludedDate() {
		return excludedDate;
	}

	public void setExcludedDate(String[] excludedDate) {
		this.excludedDate = excludedDate;
	}

	private int giorniDaPianificare = 30;

	public int getGiorniDaPianificare() {
		return giorniDaPianificare;
	}

	public void setGiorniDaPianificare(int giorniDaPianificare) {
		this.giorniDaPianificare = giorniDaPianificare;
	}

	public int getGiorniDaOggi() {
		return giorniDaOggi;
	}

	public void setGiorniDaOggi(int giorniDaOggi) {
		this.giorniDaOggi = giorniDaOggi;
	}

	private int giorniDaOggi = 1;

	@Autowired
	private PianificazioneAttivitaService pianificazioneAttivitaService;

	public void fixedDelayTask() {
		logger.info(" Eseguo la schedulazione attivita");
		pianificazioneAttivitaService.aggiornaPianificazioni(giorniDaPianificare, giorniDaOggi, excludedDate);
	}

}
