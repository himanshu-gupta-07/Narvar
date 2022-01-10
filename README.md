# URL Shortner
- Overview of Solution:
1. Since 100 millions users are using a day. Here is estimation of storage needed for Service which can provide support to 10 years:
  1.1 Write Operations : 100 million URLs are generated per day, so write operation per second: 100 million/24/3600 = 1160.
  1.2. Read Operations: Assuming ratio of read and write is 10:1. Read operations per second is 11600.
  1.3. Assuming service will support for 10 years. So total no of records is 100 million*365*10 = 365 billions record.
  1.4. Assuming average length of URl is 100.
  1.5. Storage requires over 10 years is 365 billion*100 bytes = 36.5 TB.
2. API Endpoints:
  2.1. We are using RestApis to create two APIs one GET API- for getting longURL and another POST API- for building shortURL and storing it to Database.
  2.1. We are using URL redirecting 301 when GET API is called. 301 redirect saves the longURL to browser cache and response quick if we request GET API again.
3. Implementation:
  3.1. Data Model: We will be using relational Database model which has 3 columns (Id, LongURL, ShortURL).
  3.2. Services: We would be defining two functions inside our service one shortening service (UrlShortner(String longUrl)) and redirecting service (getURL(String shortUrl)).
    3.2.1. We would be defining some hash function which can convert longURL to shortURL. We will be shortening our URL to length of hashValue. Since we have 365 billion records and our hashValue can consist of character [0-9,a-z,A-Z] total 10+26+26 = 62 characters. 62^n>=365 billion. That gives n=7. So, hash value length is 7.
    3.2.2. There are main 3 hash functions which we can use to get our hash values. They are CRC32, MD5 and SHA-I. But to make our service we are using Base 62 conversion. Please find java file (UrlService.java) for service logic. We are using hashmaps to make it service runnable but in large scale we will be using database as mentioned in 3.1. point.
