# Huffman Coding in Java

This project implements **Huffman Coding**, a lossless data compression algorithm. Huffman Coding is based on the frequency of characters in a file or data stream, where more frequent characters are represented by shorter binary codes and less frequent characters by longer codes. This makes it highly efficient for compressing data with repeating patterns.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
- [License](#license)

## Introduction

Huffman Coding is one of the most widely used algorithms for file compression. This Java implementation provides the basic functionality to encode and decode files or strings using Huffman Coding.

### How Huffman Coding Works:

1. **Frequency Calculation**: The algorithm calculates the frequency of each character in the input data.
2. **Tree Construction**: A binary tree is constructed where the characters with lower frequency are stored deeper.
3. **Code Generation**: Assigns shorter binary codes to frequently occurring characters and longer codes to the less frequent ones.
4. **Compression/Decompression**: The input is then compressed using the generated codes, and can later be decompressed to retrieve the original data.

## Features

- Encodes and decodes any text file using Huffman coding.
- Handles both small and large data efficiently.
- Lossless compression.
- Supports custom input strings or files.

## Getting Started

### Prerequisites

To run this project, you need the following software installed on your machine:

- [Java JDK 8+](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
- A terminal or IDE (like IntelliJ IDEA, Eclipse, etc.) to run Java programs.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github/AaronDavey123/Huffman-Coding


## License
This project is licensed under the MIT License - see the LICENSE file for details.
