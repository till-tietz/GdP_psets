
public class Vollmond {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);

		if(n < 0){
            System.out.println("argument has to a be non-negative integer");
			System.exit(-1);
        }

		// array of month names
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
		
		// it's immediately obvious that Januar 2022 has two full moons
		// so we can elminiate computation for that trivial case. 
		// We therefore start at day 30 in month 0 and initialize 
		// months_found and months_two_moons accordingly    
		int year = 2022;
		int month = 0;
		int day = 30;

		// boolean indicating leap year
		// set to false initially as 2022 is not a leap year
		boolean leap = false;

		// number of months with two full moons found   
		int months_found = 1;

		// string output
		// it seemed logical to just print an empty string when n = 0
		String months_two_moons;
		if(n == 0){
			months_two_moons = "";
		} else {
			months_two_moons = "2022, Januar \n";
		}

		while(months_found < n){
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

				// update leap year
				leap = (year % 4 == 0) && (year % 100 != 0);
				leap = leap || (year % 400 == 0); 

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
			// check if current full moon and previous full moon 
			// happened in the same month 
			if (month == month_new) {
				months_found = months_found + 1;
				months_two_moons = months_two_moons + year + ", " + months[month_new] + "\n";
			}
			day = day_new;
			month = month_new;
		}

		System.out.println(months_two_moons);
	}

}
