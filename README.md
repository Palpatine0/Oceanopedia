# Oceanopedia

## Introduction

Oceanopedia is a comprehensive ocean knowledge article management system. It features a robust admin panel with four
primary sections designed for effective content and user management:

## Features

- `User Management` : Inspect and manage user accounts, including details and activity logs.
- `Article Management` : Create, edit, and organize articles with rich text formatting and multimedia support.
- `Banner Management`  Customize website banners for promotions and announcements.
- `Search Functionality` : Utilize Elasticsearch for robust and efficient search capabilities across articles.
- `Microservices Architecture` : Built with Spring Boot and Spring Cloud for scalable and maintainable service management.
- `Data Storage` : Use MongoDB for flexible and efficient data storage solutions.

## Technologies Used

- `Spring Boot`: Simplifies the bootstrapping and development of new Spring applications.
- `Spring Cloud`: Facilitates building robust microservices using patterns like service discovery, configuration
  management, and routing.
- `MongoDB`: NoSQL database used for storing application data.
- `Redis`: In-memory data structure store, used as a database, cache, and message broker.
- `Elasticsearch`: Search engine based on the Lucene library, perfect for powerful search functionalities.
- `Vue.js` and `Vuetify`: Progressive JavaScript framework and material design component framework used for building the
  admin panel.

## Getting Started

### Prerequisites

Ensure you have the following installed:

- Java JDK 8
- Maven 3.6.3
- Your preferred IDE (IntelliJ IDEA, Eclipse, etc.)

### Application Ports

#### Core Services

- **Eureka server:** 8761
- **Config server:** 8764
- **Gateway server:** 4007

#### Microservices

- **Multimedia:** 9020
- **Search:** 9021
- **User:** 9022
- **Article:** 9023

#### Databases

- **MongoDB:** 27018
- **Redis:** 6380

### Installation

#### Project Installation

1. Clone the repository:

```bash
git clone git@github.com:Palpatine0/Oceanopedia.git
```

2. Navigate to the project directory:

```bash
cd Oceanopedia
```

3. Build the project with Maven:

```bash
mvn clean install
```

#### Redis Deployment (Mac)

1. Create container

```bash
docker run -d -p 6379:6380 --name dev_oceanopedia_svc_redis redis:7.2-rc2 --requirepass root
```

#### MongoDB Deployment (Mac)

1. Create container

```bash
docker run -d -p 27018:27017 --name dev_oceanopedia_svc_mongodb mongo:4.4.19-rc2 --auth
```

2. Enter MongoDB

```bash
docker exec -it dev_oceanopedia_svc_mongodb bash
```

```bash
mongo
```

3. Set authentication

```bash
use admin
db.createUser({ user: "root", pwd: "root", roles: [{ role: "root", db: "admin" }] })
db.auth("root", "root");
```

## Contributing

We welcome contributions! Any contributions are greatly appreciated.

#### To contribute:

1. Fork the Project
2. Create your Feature Branch (git checkout -b feature/AmazingFeature)
3. Commit your Changes (git commit -m 'Add some AmazingFeature')
4. Push to the Branch (git push origin feature/AmazingFeature)
5. Open a Pull Request

## License

This project is distributed under the MIT License. See the LICENSE file for more information.

## Contact

- Project Link: `https://github.com/Palpatine0/Oceanopedia`

- Snapchat: `emperorsidious0`

<hr>

## Preview

### Admin

<div align="center">
  <img width="400" src="https://i.imghippo.com/files/AZZ1O1719410861.jpg" alt="" border="0">
  <img width="400" src="https://i.imghippo.com/files/wzn8w1719419712.jpg" alt="" border="0">
  <img width="400" src="https://i.imghippo.com/files/3F84T1719422749.jpg" alt="" border="0">
  <img width="400" src="https://i.imghippo.com/files/sz39K1719422868.jpg" alt="" border="0">
  <img width="400" src="https://i.imghippo.com/files/NZUgZ1719423069.jpg" alt="" border="0">
  <img width="400" src="https://i.imghippo.com/files/z9mjn1719423130.jpg" alt="" border="0">
</div>
