# Simple UrlShortener-API

## Installation

```
# Clone this project from github
git clone https://github.com/StepanenkoDmytro/UrlShortener

# Clears the target directory and builds the project
mvn clean install
```

## Run Project
#### You can start this project using
```
mvn clean spring-boot:run
```

## How to use

### Make Requests

#### Register User:

POST /api/v1/registration<br>
Host: localhost:8080<br>
Content-Type: JSON
```
{
    "username": "user",
    "password": "password"
}
```

#### Get short link:

POST /api/v1/shortener<br>
Host: localhost:8080<br>
Content-Type: JSON
```
{
"fullUrl": "https://docs.google.com/document/d/12ENUq_DSzDJSdZO_HCcrL6XYMDCO7E8fD40ULTtVrQA/edit"
}
```

#### Get a list of links:

GET /api/v1/list-links
