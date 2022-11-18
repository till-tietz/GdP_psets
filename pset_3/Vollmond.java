
public class Vollmond {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);

		// array monate
		String[] months = { "Januar", "Februar", "Maerz", "April", "Mai", "Juni", "Juli", "August", "September",
				"Oktober", "November", "Dezember" };
		// array end-tage jedes monats für normale Jahre und Schaltjahre
		int[] month_days = { 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365 };
		int[] month_days_leap = { 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366 };

		// Jahr, Monat und Tag aktuell
		int year = 2022;
		int month = 0;
		int day = 1;

		// boolean für Schaltjahr, Startjahr 2021 ist kein Schaltjahr
		boolean leap = false;

		// gefundene Anzahl von Monaten mit zwei Vollmonden
		int months_found = 0;

		// string output
		String moons_date = "";

		while (months_found != n) {

			// nächster vollmond
			int day_new = day + 29;
		
			// testen ob im nächsten Jahr
			int year_end;
			if (leap) {
				year_end = 366;
			} else {
				year_end = 365;
			}

			if (day_new > year_end) {
				year = year + 1;

				if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
					leap = true;
				} else {
					leap = false;
				}

				day_new = day_new - year_end;
			}
			
			// monat
			int month_new = 0;
			if (leap) {
				while (true) {
					if (day_new <= month_days_leap[month_new]) {
						break;
					}
					month_new = month_new + 1;
				}
			} else {
				while (true) {
					if (day_new <= month_days[month_new]) {
						break;
					}
					month_new = month_new + 1;
				}
			}

			if (month == month_new) {
				months_found = months_found + 1;
				moons_date = moons_date + year + ", " + months[month_new] + "\n";
			}
			day = day_new;
			month = month_new;
		}

		System.out.println(moons_date);
	}

}
