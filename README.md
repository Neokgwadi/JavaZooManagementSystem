# 🦁 Java Zoo Management System

A comprehensive console-based zoo management system built in Java that allows you to manage animals, enclosures, health records, and more.

## 📋 Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Usage](#usage)
- [Available Animals](#available-animals)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

- **Animal Management**: Add, view, remove, and list animals with detailed information
- **Multiple Animal Types**: Support for Lions, Dolphins, Elephants, Penguins, and Monkeys
- **Enclosure Management**: Create and manage different habitat enclosures
- **Health Records**: Track medical history and health status for each animal
- **Data Persistence**: Save and load zoo data to/from CSV files
- **Statistics**: View comprehensive zoo statistics by type and habitat
- **Feeding System**: Automated feeding functionality for all animals
- **Unique ID Generation**: Automatic generation of unique IDs for new animals

## 🛠 Technologies Used

- **Java**: Core programming language
- **Object-Oriented Design**: Classes, interfaces, inheritance, and polymorphism
- **File I/O**: CSV-based data persistence
- **Date/Time API**: LocalDate for tracking animal arrival dates
- **Collections Framework**: HashMap, ArrayList for data management

## 📁 Project Structure

```
Java Zoo Management System/
├── src/
│   └── main/
│       └── java/
│           ├── Animal.java              # Abstract base class for all animals
│           ├── AnimalFactory.java       # Factory for creating animal instances
│           ├── Zoo.java                 # Main zoo management class
│           ├── ZooConsoleApp.java       # Console-based user interface
│           ├── ZooStorage.java          # Data persistence (CSV)
│           ├── Enclosure.java           # Habitat enclosure management
│           ├── HealthRecord.java        # Medical records for animals
│           ├── Lion.java                # Lion implementation
│           ├── Dolphin.java             # Dolphin implementation
│           ├── Elephant.java            # Elephant implementation
│           ├── Penguin.java             # Penguin implementation
│           ├── Monkey.java              # Monkey implementation
│           ├── Runner.java              # Interface for running animals
│           └── Swimmer.java             # Interface for swimming animals
├── target/
│   └── classes/                         # Compiled .class files
└── README.md                            # This file
```

## 📋 Prerequisites

- **Java Development Kit (JDK)**: Version 11 or higher
- **Command Line Interface**: Windows Command Prompt, PowerShell, or terminal

## 🚀 Installation & Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/java-zoo-management.git
   cd java-zoo-management
   ```

2. **Compile the project**:
   ```bash
   # Windows
   javac -d target\classes src\main\java\*.java

   # Linux/Mac
   javac -d target/classes src/main/java/*.java
   ```

3. **Run the application**:
   ```bash
   # Windows
   java -cp target\classes ZooConsoleApp

   # Linux/Mac
   java -cp target/classes ZooConsoleApp
   ```

## 🎮 Usage

The application provides a menu-driven interface with the following options:

### Main Menu
1. **Add Animal** - Add new animals to the zoo
2. **List All Animals** - Display all animals with their details
3. **View Animal Details** - Get detailed information about a specific animal
4. **Remove Animal** - Remove an animal from the zoo
5. **Statistics** - View zoo statistics and analytics
6. **Manage Enclosures** - Create and manage habitat enclosures
7. **Health Records** - View and manage animal medical records
8. **Feed Animals** - Feed all animals in the zoo
9. **Exit** - Save data and exit the application

### Adding Animals
When adding animals, you'll need to provide:
- Animal type (Lion, Dolphin, Elephant, Penguin, Monkey)
- Animal name
- Age

The system automatically generates a unique ID for each animal.

### Managing Enclosures
Create enclosures with different habitat types:
- **Savanna** - For lions and elephants
- **Aquatic** - For dolphins and penguins
- **Tropical Forest** - For monkeys
- **Custom** - Define your own habitat type

## 🐾 Available Animals

| Animal | Type | Habitat | Diet | Special Ability |
|--------|------|---------|------|----------------|
| 🦁 Lion | Mammal | Savanna | Carnivore | Running |
| 🐬 Dolphin | Mammal | Aquatic | Carnivore | Swimming |
| 🐘 Elephant | Mammal | Savanna | Herbivore | Running |
| 🐧 Penguin | Bird | Aquatic | Carnivore | Swimming |
| 🐒 Monkey | Mammal | Tropical Forest | Omnivore | Running |

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Development Guidelines

- Follow Java naming conventions
- Add comments for complex logic
- Write clear commit messages
- Test your changes thoroughly

## 📝 Future Enhancements

- [ ] Graphical User Interface (GUI)
- [ ] Database integration (instead of CSV)
- [ ] Animal breeding system
- [ ] Visitor management
- [ ] Financial tracking
- [ ] Advanced reporting features
- [ ] Multi-language support


## 👥 Authors

- Neo Kgwadi - *Initial work* - [YourGitHub](https://github.com/yourusername)

## 🙏 Acknowledgments

- Inspired by real zoo management systems
- Thanks to the Java community for excellent documentation
- Icons from various open-source projects


