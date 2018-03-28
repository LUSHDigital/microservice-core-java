# Lush Digital - Micro Service Java Core
A set of core functionality and convenience classes for a Spring boot microservice.

## Description

This includes an information route that could be used by a service registry, it also includes a health
check route to verify your microservice is working.

The package also contains some convenience classes to help develop microservices.

## Package Contents
* Core micro service controller
* Base and Exception enumeration class.
* Swagger config.
* Exception handling trait
* JSON and Endpoint Response trait

## Installation
Install the package as normal:

build.gradle file in yourService (Gradle version)

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```bash
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency

```bash
	dependencies {
		compile 'com.github.gameover3:micoservice-core-java:1.0.7'
	}
```