# glfw

## Leiningen Dependency

`[glfw "0.1.0-SNAPSHOT"]`

## Introduction

This library provides clojure bindings to the [glfw](http://www.glfw.org/). Complete documentation is available at 

## Usage

This api mostly mirros the c api. Some aspects of c's GLFW involve mutable arguments, whereas this api exposes that functionality without requiring mutability. For instance [glfwGetWindowSize](http://www.glfw.org/docs/latest/group__window.html#gaeea7cbc03373a41fb51cfbf9f2a5d4c6) has pointer arguments for the width and height, wheras this libray exposes `get-window-size` as a function with only one argument that simply returns a two element vector containing the indicated dimensions.
