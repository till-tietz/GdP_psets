
public class Vollmond {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);

		// array months
		String[] months = { 
			"Januar", "Februar", "Maerz", "April", 
			"Mai", "Juni", "Juli", "August", 
			"September","Oktober", "November", "Dezember" 
		};
		// array of end days for each month in regular and leap years
		int[] month_days = { 
			31, 59, 90, 120, 151, 181, 
			212, 243, 273, 304, 334, 365 
		};
		int[] month_days_leap = { 
			31, 60, 91, 121, 152, 182, 
			213, 244, 274, 305, 335, 366 
		};

		// initial year,month and day  
		int year = 2022;
		int month = 0;
		int day = 1;

		// boolean indicating leap year
		// set to false initially as 2022 is not a leap year
		boolean leap = false;

		// number of months with two full moons found   
		int months_found = 0;

		// string output
		String moons_date = "";

		while (months_found != n) {

			// day of next full moon 
			int day_new = day + 29;
		
			// year end days for leap and regular years
			int year_end;
			if (leap) {
				year_end = 366;
			} else {
				year_end = 365;
			}
			// check if new year has started
			if (day_new > year_end) {
				// updat year 
				year = year + 1;
				// check if leap year
				if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
					leap = true;
				} else {
					leap = false;
				}
				// set correct day 
				day_new = day_new - year_end;
			}
			
			// figure out month 
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
			// check if current full mon and previous full moon 
			// happened in the same month 
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
