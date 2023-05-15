# DataAggregatorApp

This is a MicroService that consumes a Kafka Topic containing information from the Italian Goverment Lombardia region API with regards of the ammount of people vaccinated in each Comune.

The data consumed is then stored in a MongoDB database.

The application then implements a controller with calls to different methods to fetch data from the database and retrieve it.

This are the HTTP GET requests to issue for each method:

1 - Get total people vaccinated with 1 dose: /getTotalNumberOfOneDose

2 - Get total people vaccinated with 2 doses: /getTotalNumberOfTwoDoses

3 - Get total people vaccinated with 1 dose from a specific province: /getTotalNumberOfOneDoseFromProvince/{siglaProvince}

4 - Get total people vaccinated with 2 doses from a specific province: /getTotalNumberOfTwoDosesFromProvince/{siglaProvince}

5 - Get a list of comunes ordered sorted in descending order by ammount of people with 2 doses: /getComunesOrderedByTwoDoses

6 - Get a list of comunes ordered sorted in descending order by ammount of people with 2 doses from a specific province: /getComunesOrderedByTwoDosesFromProvince/{siglaProvince}

7 - Get the comune with more first dose applied: /getComuneWithMoreOneDose

8 - Get the comune with more second dose applied: /getComuneWithMoreTwoDoses

9 - Get the comune with the least first dose applied: /getComuneWithLessOneDose

10 - Get a list of comunes with the most first dose applied by specific province: /getComuneWithMoreOneDoseFromProvince/{siglaProvince}

11 - Get a list of comunes with the most second dose applied by specific province: /getComuneWithMoreTwoDosesFromProvince/{siglaProvince}
