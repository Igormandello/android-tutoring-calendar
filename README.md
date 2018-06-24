# Android Tutoring Calendar

Project to learn the Andoid Studio IDE and use it with a REST API

---
## Requirements

For the database, you need to have SQL Server installed.

For the client, you will need android studio.

For the API, you will need Node.js and a node global package, Yarn, installed on your environement.

### SQL Server
  You can find SQL Server on [official Microsoft website](https://www.microsoft.com/en-us/sql-server/sql-server-downloads)

### Android Studio
  -You can find Android Studio on [official Android Studio website](https://developer.android.com/studio/)

### Node
- #### Node installation on Windows

  Just go on [official Node.js website](https://nodejs.org/) and download the installer.
Also, be sure to have `git` available in your PATH, `npm` might need it (You can find git [here](https://git-scm.com/)).

- #### Node installation on Ubuntu

  You can install nodejs and npm easily with apt install, just run the following commands.

      $ sudo apt install nodejs
      $ sudo apt install npm

- #### Other Operating Systems
  You can find more information about the installation on the [official Node.js website](https://nodejs.org/) and the [official NPM website](https://npmjs.org/).

If the installation was successful, you should be able to run the following command.

    $ node --version
    v8.11.3

    $ npm --version
    6.1.0

If you need to update `npm`, you can make it using `npm`! Cool right? After running the following command, just open again the command line and be happy.

    $ npm install npm -g

###
### Yarn installation
  After installing node, this project will need yarn too, so just run the following command.

      $ npm install -g yarn

---

## Install

    $ git clone https://github.com/Igormandello/android-tutoring-calendar
    $ cd android-tutoring-calendar/android-tutoring-api/
    $ yarn install

## Configure app

The necessary tables can be created running the `tutor.sql` and `tutor_schedule.sql` files in `android-tutoring-calendar/android-tutoring-calendar-db/` folder

Create a `.env` file in `android-tutoring-calendar/android-tutoring-api/` then edit it with your settings. The file must have the following format:

    USER=<db_username>
    PASS=<db_password>
    HOST=<db_servername>
    DATABASE=<your_database>

It has the sensitive data about your database, so keep it safe :)

After setting the API up, edit the file API_URL const in `android-tutoring-calendar/tutoring-calendar-client/app/src/main/java/utils/Utils.java`, it will look like this:

```java
public static final String API_URL = "http://YOUR_LOCAL_IP:3000/api";
```

## Running project

  After running the SQL Commands, you can run the API:

    $ yarn start

  Once the API is running, you just need to execute the android project.