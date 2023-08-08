# MetroFareTravelCart
This is console based application,which deals with metro ticket fare calculation.  This project contains 2 programs, one program using Collection and another using Database.

1:- MetroFareController.java is stand alone concole based application where data is stored in collection so this class can be directly run. 

2:-MetroFareControllerDbBased.java , this class  is stand alone concole based application where data is stored in database. 
  Database Details:- DB Configurations are in DatabaseConfig.java class and Need to create following 3 tables for program execution.
                     A:- create table Fare(FromZoneId int,ToZoneId int,PeakHourFare int,OffPeakHourFare int,DailyCap int,WeeklyCap int);
                        insert into Fare values(1,1,30,25,100,500);
                        insert into Fare values(1,2,35,30,120,600);
                        insert into Fare values(2,1,35,30,120,600);
                        insert into Fare values(2,2,25,20,80,400);

                     B:- create table PeakHour(FromTime varchar(10), ToTime varchar(10),Type varchar(12));
                      insert into PeakHour values(7, 10.30, 'weekday');
                      select * from PeakHour;
                      insert into PeakHour values(17, 20, 'weekday');
                      insert into PeakHour values(9,11,'weekend');
                      insert into PeakHour values(18,22,'weekend');

                    C:- create table JourneyDetails(UserId varchar(10), JourneyDateTime varchar(50), JourneyDate date, FromZoneId int,ToZoneId int, Amount int);

