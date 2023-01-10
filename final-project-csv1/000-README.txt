This project .zip file contains starter code for working with the three datasets
outlined in the CDH Ideation Workshop. See the included .pdf files for the
workshop slides and handout.

See this video for a walkthrough of this project .zip file:
https://www.loom.com/share/0845c1fd957245e48a0a1b77c2812b9e

/**********************************************************************
 * STARTER CODE                                                       *
 **********************************************************************/

CSVReader.java (library for parsing .csv files)
DataAnalysisClient.java (sample client code for using the CSVReader library)

See the first comment in DataAnalysisClient.java for more details.

/**********************************************************************
 * DATA                                                               *
 **********************************************************************/

The data directory contains all the .csv files for the three datasets.

data/
  bechdel_test.csv (Bechdel Test for Movies)
  database_file.csv (Endangered Languages Project)
  SCoData_books_v1.1_2021-01.csv (Library Lending Data: Shakespeare and Company)
  SCoData_events_v1.1_2021-01.csv (Library Lending Data: Shakespeare and Company)
  SCoData_members_v1.1_2021-01.csv (Library Lending Data: Shakespeare and Company)

1. The Endangered Languages Project
database_file.csv was downloaded from this URL by clicking the
"Download the database" button: https://www.endangeredlanguages.com/userquery/

The first row in the .csv file (i.e., header) was added from the PDF of the slides.

Please cite the Endangered Languages dataset as follows:
Catalogue of Endangered Languages. 2021. University of Hawaii at Manoa. http://www.endangeredlanguages.com

2. Library Lending Data: Shakespeare and Company
The three .csv files for the Shakespeare and Company project dataset
(data/SCoData_books_v1.1_2021-01.csv, data/SCoData_events_v1.1_2021-01.csv,
data/SCoData_members_v1.1_2021-01.csv) were downloaded from this URL by clicking
the "CSV" buttons to download the respective files:
https://shakespeareandco.princeton.edu/about/data/

Please see the above website for how to properly cite each of the datasets.

3. The Bechdel Test for Movies
data/bechdel_test.csv was downloaded from this Google sheet by selecting
File > Download > Comma-separated values (.csv current sheet):
https://docs.google.com/spreadsheets/d/1_Tg-xQZeb4wgG6WxaDfcQH3G1-rMw7gAu8XJpJm0Cww/edit#gid=947183866

This Google sheet was created from the .csv file found at this URL:
https://github.com/fivethirtyeight/data/blob/master/bechdel/movies.csv

This data was compiled for a 2014 FiveThirtyEight article titled
"The Dollar-And-Cents Case Against Hollywood's Exclusion of Women"
(https://fivethirtyeight.com/features/the-dollar-and-cents-case-against-hollywoods-exclusion-of-women/).

Please cite the FiveThirtyEight article when using their dataset.

/**********************************************************************
 * LIBRARIES                                                          *
 **********************************************************************/

The lib directory contains the .jar files for the CommonsCSV library. For
more information about that library, see https://commons.apache.org/csv/.

lib/
    commons-csv-1.0.jar
    commons-csv-1.0-javadoc.jar
    commons-csv-1.0-sources.jar

/**********************************************************************
 * LICENSE                                                            *
 **********************************************************************/

 The MIT License only applies to the code in this project (not the data
 files). Please see the original sources of the data files for usage
 rights information.
