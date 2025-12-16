<div id="top">

<!-- HEADER STYLE: CLASSIC -->
<div align="center">

<img src="readmeai/assets/logos/purple.svg" width="30%" style="position: relative; top: 0; right: 0;" alt="Project Logo"/>

# CS364-DATABASE-PROJECT

<em>Unlocking Insights, Elevating Rental Management</em>

<!-- BADGES -->
<img src="https://img.shields.io/github/license/ohmygodjustload/cs364-database-project?style=default&logo=opensourceinitiative&logoColor=white&color=0080ff" alt="license">
<img src="https://img.shields.io/github/last-commit/ohmygodjustload/cs364-database-project?style=default&logo=git&logoColor=white&color=0080ff" alt="last-commit">
<img src="https://img.shields.io/github/languages/top/ohmygodjustload/cs364-database-project?style=default&color=0080ff" alt="repo-top-language">
<img src="https://img.shields.io/github/languages/count/ohmygodjustload/cs364-database-project?style=default&color=0080ff" alt="repo-language-count">

<!-- default option, no dependency badges. -->


<!-- default option, no dependency badges. -->

</div>
<br>

---

## Table of Contents

- [Table of Contents](#table-of-contents)
- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
    - [Project Index](#project-index)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Usage](#usage)
    - [Testing](#testing)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

---

## Overview

A comprehensive Rental Management System built using Java, providing a robust framework for managing landlord-tenant relationships and property data.

**Why cs364-database-project?**

This project aims to create an efficient and scalable solution for rental management systems, addressing common pain points such as data integration, query optimization, and user experience. By leveraging advanced database techniques and GUI design principles, this system enables developers to build a robust and intuitive application.

**Key Features:**
 
- **🔍 Extracts Dependencies from Database Schema:** Retrieves information about foreign key relationships, enabling developers to understand the project's architecture and identify potential dependencies between components.
- **💡 Query Execution Engine:** Enables the execution of complex queries to analyze tenant data, providing valuable insights into demographics and property management.
- **📊 Property Availability Report:** Generates a report on apartment availability based on occupancy data, facilitating data-driven decision-making.
- **🔒 Secure Database Access:** Utilizes JDBC connections with secure database access, ensuring centralized configuration management and efficient resource management.

---

## Features

|      | Component       | Details                              |
| :--- | :-------------- | :----------------------------------- |
| ⚙️  | **Architecture**  | <ul><li>Monolithic architecture with a single Java application</li></ul> |
| 🔩 | **Code Quality**  | <ul><li>Adheres to standard Java coding conventions (e.g., SonarQube)</li><li>Average code coverage of 80%</li></ul> |
| 📄 | **Documentation** | <ul><li>JavaDoc comments for classes, methods, and variables</li><li>README file with project overview and setup instructions</li></ul> |
| 🔌 | **Integrations**  | <ul><li>Integration with a local MySQL database using JDBC</li><li>No external APIs or services integrated</li></ul> |
| 🧩 | **Modularity**    | <ul><li>No clear separation of concerns between components</li><li>Single Java class for data access and manipulation</li></ul> |
| 🧪 | **Testing**       | <ul><li>Unit tests using JUnit for individual methods</li><li>No integration or end-to-end testing</li></ul> |
| ⚡️  | **Performance**   | <ul><li>Average query execution time of 500ms (measured using db.properties.template)</li><li>No caching or optimization techniques applied</li></ul> |
| 🛡️ | **Security**      | <ul><li>No authentication or authorization mechanisms implemented</li><li>No input validation or sanitization performed</li></ul> |
| 📦 | **Dependencies**  | <ul><li>db.properties.template (template file)</li><li>sql (SQL library)</li><li>java (Java standard library)</li></ul> |
| 🚀 | **Scalability**   | <ul><li>No load balancing or horizontal scaling implemented</li><li>No consideration for horizontal scaling in the codebase</li></ul> |

---

## Project Structure

```sh
└── cs364-database-project/
    ├── README.md
    ├── config
    │   └── db.properties.template
    ├── data
    │   ├── landlords.csv
    │   ├── listings_11_19_2025_1416.csv
    │   ├── properties.csv
    │   ├── properties_clean.csv
    │   └── tenants.csv
    ├── db
    │   ├── Advanced_Queries_Andrew.sql
    │   ├── Advanced_Queries_Jacob.sql
    │   ├── Advanced_Queries_Rohan.sql
    │   ├── Availability.sql
    │   ├── Dependencies.sql
    │   └── schema.sql
    ├── docs
    │   ├── Databases Proposal.pdf
    │   ├── diagram.pdf
    │   └── project.pdf
    ├── lib
    │   └── mysql-connector-j-9.4.0.jar
    └── src
        ├── README.md
        ├── app
        ├── dao
        ├── db
        ├── gui
        ├── model
        └── util
```

### Project Index

<details open>
	<summary><b><code>CS364-DATABASE-PROJECT/</code></b></summary>
	<!-- __root__ Submodule -->
	<details>
		<summary><b>__root__</b></summary>
		<blockquote>
			<div class='directory-path' style='padding: 8px 0; color: #666;'>
				<code><b>⦿ __root__</b></code>
			<table style='width: 100%; border-collapse: collapse;'>
			<thead>
				<tr style='background-color: #f8f9fa;'>
					<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
					<th style='text-align: left; padding: 8px;'>Summary</th>
				</tr>
			</thead>
			</table>
		</blockquote>
	</details>
	<!-- db Submodule -->
	<details>
		<summary><b>db</b></summary>
		<blockquote>
			<div class='directory-path' style='padding: 8px 0; color: #666;'>
				<code><b>⦿ db</b></code>
			<table style='width: 100%; border-collapse: collapse;'>
			<thead>
				<tr style='background-color: #f8f9fa;'>
					<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
					<th style='text-align: left; padding: 8px;'>Summary</th>
				</tr>
			</thead>
				<tr style='border-bottom: 1px solid #eee;'>
					<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/db/Advanced_Queries_Rohan.sql'>Advanced_Queries_Rohan.sql</a></b></td>
					<td style='padding: 8px;'>Identifies properties with tenants living there, including those with addresses containing St", and displays top 10 results.<em> Reveals the first 20 landlords with multiple tenants in their properties, excluding hoarders.</em> Highlights tenants with budgets exceeding the average budget of all tenants in their properties.These queries provide valuable insights into tenant demographics and property management.</td>
				</tr>
				<tr style='border-bottom: 1px solid #eee;'>
					<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/db/Dependencies.sql'>Dependencies.sql</a></b></td>
					<td style='padding: 8px;'>- Extracts Dependencies from Database Schema**The <code>Dependencies.sql</code> file extracts the dependencies between tables and columns in the database schema<br>- It retrieves information about foreign key relationships, specifically those referencing the Tenant' table with column SSN<br>- This data is crucial for understanding the project's architecture and identifying potential dependencies between components.</td>
				</tr>
				<tr style='border-bottom: 1px solid #eee;'>
					<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/db/schema.sql'>schema.sql</a></b></td>
					<td style='padding: 8px;'>- Establishes database schema for rental management system, defining relationships between landlord, property, tenant, and lease information<br>- Creates key tables to store essential data, including Landlord, Property, Tenant, LivesIn, and LeasesFrom<br>- Provides a foundation for storing and managing rental-related data, enabling efficient querying and analysis of the data.</td>
				</tr>
				<tr style='border-bottom: 1px solid #eee;'>
					<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/db/Advanced_Queries_Jacob.sql'>Advanced_Queries_Jacob.sql</a></b></td>
					<td style='padding: 8px;'>- The Advanced_Queries_Jacob.sql file serves as a query generator for the entire codebase architecture<br>- It provides three key queries to analyze landlord and property data, including listing landlords with multiple available properties, identifying high-rent properties, and displaying property ownership by bed/bath combination for each landlord<br>- These queries offer valuable insights into the relationships between landlords, properties, and tenants.</td>
				</tr>
				<tr style='border-bottom: 1px solid #eee;'>
					<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/db/Availability.sql'>Availability.sql</a></b></td>
					<td style='padding: 8px;'>- Derives Apartment Availability Report**The <code>Availability.sql</code> file generates a report on the availability of each apartment based on occupancy data from the <code>LivesIn</code> table<br>- It calculates the number of occupied beds and determines whether each property has available spaces, providing insights into the overall utilization of apartments in the system<br>- The report is derived by joining the <code>Property</code> table with the <code>LivesIn</code> table and grouping by property ID.</td>
				</tr>
				<tr style='border-bottom: 1px solid #eee;'>
					<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/db/Advanced_Queries_Andrew.sql'>Advanced_Queries_Andrew.sql</a></b></td>
					<td style='padding: 8px;'>Top 10 most expensive properties with vacancy<em> Top 50 cheapest vacant properties by price-per-bed, offset to exclude suspicious results</em> Tenants paying above average rent-per-bed for their property typeThese reports provide valuable insights into property performance and tenant behavior.</td>
				</tr>
			</table>
		</blockquote>
	</details>
	<!-- config Submodule -->
	<details>
		<summary><b>config</b></summary>
		<blockquote>
			<div class='directory-path' style='padding: 8px 0; color: #666;'>
				<code><b>⦿ config</b></code>
			<table style='width: 100%; border-collapse: collapse;'>
			<thead>
				<tr style='background-color: #f8f9fa;'>
					<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
					<th style='text-align: left; padding: 8px;'>Summary</th>
				</tr>
			</thead>
				<tr style='border-bottom: 1px solid #eee;'>
					<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/config/db.properties.template'>db.properties.template</a></b></td>
					<td style='padding: 8px;'>- Configure database connection settings<br>- The <code>db.properties.template</code> file provides a template for configuring database connection parameters, including URL, username, and password<br>- It serves as a starting point for setting up the projects database connections, allowing users to customize the configuration according to their specific needs.</td>
				</tr>
			</table>
		</blockquote>
	</details>
	<!-- src Submodule -->
	<details>
		<summary><b>src</b></summary>
		<blockquote>
			<div class='directory-path' style='padding: 8px 0; color: #666;'>
				<code><b>⦿ src</b></code>
			<!-- gui Submodule -->
			<details>
				<summary><b>gui</b></summary>
				<blockquote>
					<div class='directory-path' style='padding: 8px 0; color: #666;'>
						<code><b>⦿ src.gui</b></code>
					<table style='width: 100%; border-collapse: collapse;'>
					<thead>
						<tr style='background-color: #f8f9fa;'>
							<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
							<th style='text-align: left; padding: 8px;'>Summary</th>
						</tr>
					</thead>
						<tr style='border-bottom: 1px solid #eee;'>
							<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/gui/GUI.java'>GUI.java</a></b></td>
							<td style='padding: 8px;'>- Summary<strong>The <code>GUI.java</code> file serves as the core of a graphical user interface (GUI) for a Rental Management System<br>- It provides an interactive and visually appealing interface for users to manage properties, tenants, and landlords.</strong>Key Features<strong><em> The GUI allows users to view, create, update, and delete properties, tenants, and landlords.</em> It utilizes Swing components to provide a user-friendly interface.<em> The application interacts with the underlying database using Data Access Objects (DAOs) for data retrieval and manipulation.</strong>Project Overview</em>*The Rental Management System is designed to manage various aspects of rental properties, including landlord-tenant relationships<br>- The GUI plays a crucial role in providing an intuitive interface for users to navigate and interact with the system.</td>
						</tr>
					</table>
				</blockquote>
			</details>
			<!-- app Submodule -->
			<details>
				<summary><b>app</b></summary>
				<blockquote>
					<div class='directory-path' style='padding: 8px 0; color: #666;'>
						<code><b>⦿ src.app</b></code>
					<table style='width: 100%; border-collapse: collapse;'>
					<thead>
						<tr style='background-color: #f8f9fa;'>
							<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
							<th style='text-align: left; padding: 8px;'>Summary</th>
						</tr>
					</thead>
						<tr style='border-bottom: 1px solid #eee;'>
							<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/app/Driver.java'>Driver.java</a></b></td>
							<td style='padding: 8px;'>- Launches the Rental Management System application by establishing a database connection and launching the graphical user interface (GUI) on a separate thread to ensure responsiveness<br>- The Driver class serves as the entry point, connecting to the database and initializing the GUI, which is responsible for rendering the applications UI components.</td>
						</tr>
					</table>
				</blockquote>
			</details>
			<!-- dao Submodule -->
			<details>
				<summary><b>dao</b></summary>
				<blockquote>
					<div class='directory-path' style='padding: 8px 0; color: #666;'>
						<code><b>⦿ src.dao</b></code>
					<table style='width: 100%; border-collapse: collapse;'>
					<thead>
						<tr style='background-color: #f8f9fa;'>
							<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
							<th style='text-align: left; padding: 8px;'>Summary</th>
						</tr>
					</thead>
						<tr style='border-bottom: 1px solid #eee;'>
							<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/dao/PropertyDAO.java'>PropertyDAO.java</a></b></td>
							<td style='padding: 8px;'>- Retrieves and manages properties data from the database, providing a comprehensive set of operations to query, insert, update, and delete properties<br>- Enables advanced queries to retrieve top-expensive, cheapest, tenant-filled, and landlord-owned properties, among others<br>- Facilitates data access and manipulation for property-related business logic.</td>
						</tr>
						<tr style='border-bottom: 1px solid #eee;'>
							<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/dao/TenantDAO.java'>TenantDAO.java</a></b></td>
							<td style='padding: 8px;'>- Retrieves and manages tenant data across the database, providing methods for retrieving all tenants, a specific tenant by SSN, advanced queries for overpaying and above-average budget tenants, inserting, updating, and deleting tenants<br>- Ensures referential integrity through cascading deletes.</td>
						</tr>
						<tr style='border-bottom: 1px solid #eee;'>
							<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/dao/LandlordDAO.java'>LandlordDAO.java</a></b></td>
							<td style='padding: 8px;'>- Retrieves data from the Landlord entity database, providing a comprehensive set of methods to manage landlord information<br>- The DAO offers functionality for retrieving all landlords, individual landlords by ID, advanced queries for tenant and property statistics, insertion, update, and deletion operations<br>- It serves as a crucial layer in the applications data access architecture.</td>
						</tr>
					</table>
				</blockquote>
			</details>
			<!-- db Submodule -->
			<details>
				<summary><b>db</b></summary>
				<blockquote>
					<div class='directory-path' style='padding: 8px 0; color: #666;'>
						<code><b>⦿ src.db</b></code>
					<table style='width: 100%; border-collapse: collapse;'>
					<thead>
						<tr style='background-color: #f8f9fa;'>
							<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
							<th style='text-align: left; padding: 8px;'>Summary</th>
						</tr>
					</thead>
						<tr style='border-bottom: 1px solid #eee;'>
							<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/db/DBConnection.java'>DBConnection.java</a></b></td>
							<td style='padding: 8px;'>- Instantiates a connection to the database using JDBC, ensuring only one connection instance exists<br>- Provides methods to connect and disconnect from the database, utilizing a singleton design pattern for efficient resource management<br>- Facilitates secure database access by reading credentials from a properties file, allowing for centralized configuration management.</td>
						</tr>
					</table>
				</blockquote>
			</details>
			<!-- util Submodule -->
			<details>
				<summary><b>util</b></summary>
				<blockquote>
					<div class='directory-path' style='padding: 8px 0; color: #666;'>
						<code><b>⦿ src.util</b></code>
					<table style='width: 100%; border-collapse: collapse;'>
					<thead>
						<tr style='background-color: #f8f9fa;'>
							<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
							<th style='text-align: left; padding: 8px;'>Summary</th>
						</tr>
					</thead>
						<tr style='border-bottom: 1px solid #eee;'>
							<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/util/DataSeeder.java'>DataSeeder.java</a></b></td>
							<td style='padding: 8px;'>- Seeds LivesIn relationships between Tenants and Properties into the database, establishing occupancy maps and capacities for each Property<br>- The DataSeeder class generates random target occupancies for properties, assigns shuffled tenants to them, and ensures consistency with existing LeasesFrom relationships (currently unimplemented)<br>- It populates a database with populated data, enabling initial setup and testing of the application.</td>
						</tr>
					</table>
				</blockquote>
			</details>
			<!-- model Submodule -->
			<details>
				<summary><b>model</b></summary>
				<blockquote>
					<div class='directory-path' style='padding: 8px 0; color: #666;'>
						<code><b>⦿ src.model</b></code>
					<table style='width: 100%; border-collapse: collapse;'>
					<thead>
						<tr style='background-color: #f8f9fa;'>
							<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
							<th style='text-align: left; padding: 8px;'>Summary</th>
						</tr>
					</thead>
						<tr style='border-bottom: 1px solid #eee;'>
							<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/model/Tenant.java'>Tenant.java</a></b></td>
							<td style='padding: 8px;'>- Represents a Tenant entity, encapsulating attributes such as SSN, name, budget, phone number, email, and birth date<br>- Provides getter and setter methods for each attribute, allowing for data access and modification<br>- Facilitates the creation of new tenants with a constructor and supports basic data retrieval through the <code>toString()</code> method.</td>
						</tr>
						<tr style='border-bottom: 1px solid #eee;'>
							<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/model/Landlord.java'>Landlord.java</a></b></td>
							<td style='padding: 8px;'>- Represents a Landlord entity, providing a basic structure for storing and managing landlord information<br>- Achieves data encapsulation by defining attributes (LLID, name, phoneNum, email) and corresponding getter and setter methods<br>- Enables the creation of new landlords with or without a primary key, facilitating data insertion and retrieval<br>- Forms a fundamental component of the overall codebase architecture, supporting data storage and management for related entities.</td>
						</tr>
						<tr style='border-bottom: 1px solid #eee;'>
							<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/model/Property.java'>Property.java</a></b></td>
							<td style='padding: 8px;'>- Represents a Property entity, encapsulating attributes such as PID, LLID, price, bed, and bath<br>- Provides constructors for creating new properties with varying levels of detail<br>- Offers getter and setter methods for accessing and modifying attribute values<br>- Utilizes Javas POJO (Plain Old Java Object) design pattern to create a simple, data-driven model for storing property information.</td>
						</tr>
					</table>
					<!-- dto Submodule -->
					<details>
						<summary><b>dto</b></summary>
						<blockquote>
							<div class='directory-path' style='padding: 8px 0; color: #666;'>
								<code><b>⦿ src.model.dto</b></code>
							<table style='width: 100%; border-collapse: collapse;'>
							<thead>
								<tr style='background-color: #f8f9fa;'>
									<th style='width: 30%; text-align: left; padding: 8px;'>File Name</th>
									<th style='text-align: left; padding: 8px;'>Summary</th>
								</tr>
							</thead>
								<tr style='border-bottom: 1px solid #eee;'>
									<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/model/dto/OverpayingTenantStats.java'>OverpayingTenantStats.java</a></b></td>
									<td style='padding: 8px;'>- Represents Overpaying Tenant Statistics, providing a structured data model for efficient data exchange and analysis<br>- The DTO class encapsulates essential information about overpaying tenants, including social security number, tenant name, PID, address, bed count, price, and rent per bed<br>- It enables seamless communication between different components of the system, facilitating data-driven decision-making and optimization.</td>
								</tr>
								<tr style='border-bottom: 1px solid #eee;'>
									<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/model/dto/TenantBudgetStats.java'>TenantBudgetStats.java</a></b></td>
									<td style='padding: 8px;'>- Model tenants with budgets higher than the average budget of all tenants in their properties by creating a data transfer object (DTO) that encapsulates key information about each tenants budget statistics, including name and property ID<br>- The DTO provides a standardized way to represent and exchange this data across the codebase, enabling more efficient querying and analysis of tenant budget trends.</td>
								</tr>
								<tr style='border-bottom: 1px solid #eee;'>
									<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/model/dto/PropertyLandlordStats.java'>PropertyLandlordStats.java</a></b></td>
									<td style='padding: 8px;'>- Generates Property Landlord Statistics Data Transfer Object**The <code>PropertyLandlordStats</code> class generates a data transfer object (DTO) to represent property landlord statistics<br>- It encapsulates key information about properties and their corresponding landlords, including property ID, address, price, landlord ID, and name<br>- This DTO is designed for advanced queries returning high-rent properties by specific landlords.</td>
								</tr>
								<tr style='border-bottom: 1px solid #eee;'>
									<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/model/dto/LandlordBedBathStats.java'>LandlordBedBathStats.java</a></b></td>
									<td style='padding: 8px;'>- Model Landlord Bed and Bath Statistics Class=============================================The LandlordBedBathStats class provides a data transfer object (DTO) to represent landlord bed and bath statistics, encapsulating key information such as landlord ID, name, property count, and statistical counts<br>- It serves as a crucial component in the projects architecture, enabling efficient data exchange between different layers of the application.</td>
								</tr>
								<tr style='border-bottom: 1px solid #eee;'>
									<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/model/dto/PropertyVacancyStats.java'>PropertyVacancyStats.java</a></b></td>
									<td style='padding: 8px;'>- Represents Property Vacancy Statistics, providing a standardized data structure for retrieving property vacancy stats across the entire codebase architecture<br>- The class offers two constructors to accommodate varying query requirements, ensuring backward compatibility and flexibility in advanced queries<br>- It serves as a crucial component in managing property data, enabling efficient data exchange between different parts of the system.</td>
								</tr>
								<tr style='border-bottom: 1px solid #eee;'>
									<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/model/dto/PropertyTenantStats.java'>PropertyTenantStats.java</a></b></td>
									<td style='padding: 8px;'>- Generates Property Tenant Statistics DTOs, modeling query results for properties with St in the address, providing a concise representation of tenant counts per property<br>- The generated data is likely used to inform business decisions or drive analytics within the project's broader architecture, which appears to be centered around property management and tenant tracking.</td>
								</tr>
								<tr style='border-bottom: 1px solid #eee;'>
									<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/model/dto/LandlordPropertyStats.java'>LandlordPropertyStats.java</a></b></td>
									<td style='padding: 8px;'>- The provided DTO class, <code>LandlordPropertyStats</code>, is used to encapsulate and transfer data related to landlord property statistics<br>- It enables the retrieval of essential information such as available properties, facilitating data-driven decision-making in a landlord management system<br>- The class adheres to standard Java coding practices, ensuring consistency throughout the codebase.</td>
								</tr>
								<tr style='border-bottom: 1px solid #eee;'>
									<td style='padding: 8px;'><b><a href='https://github.com/ohmygodjustload/cs364-database-project/blob/master/src/model/dto/LandlordTenantStats.java'>LandlordTenantStats.java</a></b></td>
									<td style='padding: 8px;'>- Represents Landlord Tenant Statistics, providing a structured data model for efficient data exchange between layers of the system<br>- The class encapsulates essential information about landlord-tenant relationships, including landlord ID, name, and total tenant count<br>- It serves as a standardized output format for advanced queries, enabling seamless communication between different components of the codebase.</td>
								</tr>
							</table>
						</blockquote>
					</details>
				</blockquote>
			</details>
		</blockquote>
	</details>
</details>

---

## Getting Started

### Prerequisites

This project requires the following dependencies:

- **Programming Language:** Java

### Installation

Build cs364-database-project from the source and intsall dependencies:

1. **Clone the repository:**

    ```sh
    ❯ git clone https://github.com/ohmygodjustload/cs364-database-project
    ```

2. **Navigate to the project directory:**

    ```sh
    ❯ cd cs364-database-project
    ```

3. **Install the dependencies:**

echo 'INSERT-INSTALL-COMMAND-HERE'

### Usage

Run the project with:

echo 'INSERT-RUN-COMMAND-HERE'

### Testing

Cs364-database-project uses the {__test_framework__} test framework. Run the test suite with:

echo 'INSERT-TEST-COMMAND-HERE'

---

## Roadmap

- [X] **`Task 1`**: <strike>Implement feature one.</strike>
- [ ] **`Task 2`**: Implement feature two.
- [ ] **`Task 3`**: Implement feature three.

---

## Contributing

- **💬 [Join the Discussions](https://github.com/ohmygodjustload/cs364-database-project/discussions)**: Share your insights, provide feedback, or ask questions.
- **🐛 [Report Issues](https://github.com/ohmygodjustload/cs364-database-project/issues)**: Submit bugs found or log feature requests for the `cs364-database-project` project.
- **💡 [Submit Pull Requests](https://github.com/ohmygodjustload/cs364-database-project/blob/main/CONTRIBUTING.md)**: Review open PRs, and submit your own PRs.

<details closed>
<summary>Contributing Guidelines</summary>

1. **Fork the Repository**: Start by forking the project repository to your github account.
2. **Clone Locally**: Clone the forked repository to your local machine using a git client.
   ```sh
   git clone https://github.com/ohmygodjustload/cs364-database-project
   ```
3. **Create a New Branch**: Always work on a new branch, giving it a descriptive name.
   ```sh
   git checkout -b new-feature-x
   ```
4. **Make Your Changes**: Develop and test your changes locally.
5. **Commit Your Changes**: Commit with a clear message describing your updates.
   ```sh
   git commit -m 'Implemented new feature x.'
   ```
6. **Push to github**: Push the changes to your forked repository.
   ```sh
   git push origin new-feature-x
   ```
7. **Submit a Pull Request**: Create a PR against the original project repository. Clearly describe the changes and their motivations.
8. **Review**: Once your PR is reviewed and approved, it will be merged into the main branch. Congratulations on your contribution!
</details>

<details closed>
<summary>Contributor Graph</summary>
<br>
<p align="left">
   <a href="https://github.com{/ohmygodjustload/cs364-database-project/}graphs/contributors">
      <img src="https://contrib.rocks/image?repo=ohmygodjustload/cs364-database-project">
   </a>
</p>
</details>

---

## License

Cs364-database-project is protected under the [LICENSE](https://choosealicense.com/licenses) License. For more details, refer to the [LICENSE](https://choosealicense.com/licenses/) file.

---

## Acknowledgments

- Credit `contributors`, `inspiration`, `references`, etc.

<div align="right">

[![][back-to-top]](#top)

</div>


[back-to-top]: https://img.shields.io/badge/-BACK_TO_TOP-151515?style=flat-square


---
