cmake_minimum_required(VERSION 3.12)
project(huff)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(huff
        a.out
        huff.c
        huff.h
        main.c
        Makefile
        modern-prometheus-unix.txt)
