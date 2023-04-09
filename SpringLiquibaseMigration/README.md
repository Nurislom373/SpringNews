# Spring Liquibase Migration

Liquibase as a tool for managing database schemas and data. Liquibase is a popular open-source database migration tool
that helps manage and automate database schema changes.

- Manage database schema changes: Liquibase allows you to manage database schema changes in a controlled and auditable
  manner. It provides a way to version control database changes and allows you to roll back changes if needed.
- Collaborate with teams: Liquibase enables teams to collaborate on database changes by providing a standardized way to
  manage changes across environments. It also provides tools to validate and deploy changes automatically.

## Overview

Very briefly, the core of using Liquibase is the changelog file, an XML file that describes changes that need for our
DB, at a time.

We can have many changelog files for our DB changes throughout the time.

All these are referenced in one root changelog file master.xml that is executing when Liquibase is running.

```xml
<dependency>
    <groupId>org.liquibase</groupId>
    <artifactId>liquibase-core</artifactId>
    <version>3.6.2</version>
</dependency>
```

