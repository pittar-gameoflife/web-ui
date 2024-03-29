# Web UI Project

This project uses Quarkus, the Supersonic Subatomic Java Framework!

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## How this app was bootstrapped

```shell script
mvn io.quarkus:quarkus-maven-plugin:1.10.5.Final:create \
    -DprojectGroupId=ca.pitt.demo.gameoflife \
    -DprojectArtifactId=gol-ui \
    -Dextensions="smallrye-reactive-messaging-kafka"
cd supervisor
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
mvn compile quarkus:dev
```

```
"version": "0.2.0",
  "configurations": [
    {
        "type": "netcoredbg",
        "request": "launch",
        "program": "/projects/dotnet-app/PoliticalFinanceReturn/PFR.Web.UI/bin/Debug/netcoreapp3.1/PFR.Web.UI.dll",
        "args": [],
        "name": ".NET Core Launch (console)",
        "stopAtEntry": false,
        "console": "internalConsole"
    }
    ]
```