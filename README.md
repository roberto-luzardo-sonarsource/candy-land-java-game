# Candy Land Game

A Java implementation of the classic Candy Land board game supporting up to 3 players with a graphical user interface. This project includes automated testing, continuous integration, and code quality analysis with SonarQube.

## Features

- **1-3 Players**: Support for up to 3 players
- **Graphical User Interface**: Full GUI with visual game board and controls
- **Classic Gameplay**: Traditional Candy Land rules with colored cards and special character cards
- **Interactive Board**: Visual representation of the 134-space board with colored spaces
- **Player Tracking**: Real-time display of player positions and game progress
- **Game Controls**: Easy-to-use interface for drawing cards and managing turns
- **Complete Game Board**: 134-space board with special character locations
- **Turn-based Play**: Players take turns drawing cards and moving
- **Win Condition**: First player to reach the end wins
- **Automated Testing**: Comprehensive unit test suite with code coverage
- **Code Quality**: Continuous code quality analysis with SonarQube
- **CI/CD Pipeline**: GitHub Actions workflow for automated builds and analysis

## Screenshots

The game features:
- **Player Setup Dialog**: Enter player names and select number of players
- **Visual Game Board**: Colorful board with snake-like path and player positions
- **Game Controls Panel**: Draw cards, view game status, and track player positions
- **Menu System**: New game, game rules, and help options

## Project Structure

```
src/
├── main/java/com/example/candyland/
│   ├── gui/
│   │   ├── CandyLandGUI.java       # Main GUI window and application entry point
│   │   ├── PlayerSetupDialog.java  # Player setup dialog
│   │   ├── GameBoardPanel.java     # Visual game board panel
│   │   └── GameControlPanel.java   # Game controls and information panel
│   ├── CandyLandGame.java          # Core game logic and rules
│   ├── Player.java                 # Player representation
│   ├── Board.java                  # Game board with spaces and special locations
│   ├── Card.java                   # Game cards (color and character cards)
│   ├── Deck.java                   # Card deck management
│   └── Color.java                  # Board colors enumeration
└── test/java/com/example/candyland/
    ├── PlayerTest.java             # Unit tests for Player class
    └── BoardTest.java              # Unit tests for Board class
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## CI/CD Pipeline

This project uses GitHub Actions for continuous integration with automated testing and code quality analysis.

### Workflow Triggers
- **Push to main branch**: Full build, test, and SonarQube analysis
- **Pull requests**: Build, test, and analysis for code review

### Pipeline Features
- **Automated Testing**: Runs complete test suite on every commit
- **Code Coverage**: Generates coverage reports with JaCoCo
- **SonarQube Analysis**: Static code analysis for quality, security, and maintainability
- **Dependency Caching**: Optimized build times with Maven and SonarQube caching
- **Artifact Upload**: Test results and analysis artifacts for review

### Setting Up SonarQube Integration

To enable SonarQube analysis in your fork:

1. **Create a SonarCloud account** at https://sonarcloud.io
2. **Set up your organization** or use an existing one  
3. **Add repository secrets** in GitHub Settings > Secrets and variables > Actions:
   - `SONAR_TOKEN`: Your SonarCloud authentication token
   - `SONAR_ORGANIZATION`: Your SonarCloud organization key

#### Getting Your SonarCloud Token
1. Log in to SonarCloud
2. Go to **My Account** > **Security** 
3. Generate a new token with appropriate permissions
4. Copy the token and add as `SONAR_TOKEN` in GitHub repository secrets

### Quality Gates
The SonarQube analysis checks for:
- **Code Coverage**: Minimum coverage thresholds
- **Security**: Vulnerabilities and security hotspots
- **Maintainability**: Code smells and technical debt
- **Reliability**: Bugs and potential runtime issues

## Building and Running

### Using Maven Commands

1. **Compile the project:**
   ```bash
   mvn compile
   ```

2. **Run tests:**
   ```bash
   mvn test
   ```

3. **Run the GUI game:**
   ```bash
   mvn exec:java
   ```

4. **Clean and rebuild:**
   ```bash
   mvn clean compile
   ```

### Using VS Code Tasks

This project includes predefined VS Code tasks that you can run from the Command Palette (`Ctrl+Shift+P` or `Cmd+Shift+P`):

- **Build Candy Land Game** - Compile the project
- **Run Candy Land Game** - Start the GUI game
- **Test Candy Land Game** - Run unit tests
- **Clean and Build** - Clean and recompile

### Using VS Code Debug Configuration

A debug configuration is available for running the game in debug mode:

- **Debug Candy Land Game** - Launch with debugger attached

## How to Play

1. **Start the Game**: Run the application using one of the methods above
2. **Setup Players**: 
   - A dialog will appear asking for the number of players (1-3)
   - Enter names for each player
   - Click "Start Game" to begin
3. **Playing the Game**:
   - The game board will display with colored spaces in a snake-like pattern
   - Players are represented by colored circles on the board
   - Click "Draw Card" to take your turn
   - Move according to the card drawn:
     - Color cards: Move to the next space of that color
     - Double color cards: Move to the second occurrence of that color
     - Special character cards: Move to the character's specific location
4. **Game Information**:
   - Current player is displayed in the control panel
   - Player positions are shown in real-time
   - Game log tracks all moves and events
5. **Winning**: The first player to reach or pass the finish line (golden border) wins!

## GUI Features

### Main Window
- **Visual Game Board**: See the entire 134-space board with colored spaces
- **Player Positions**: Real-time tracking of all players with colored markers
- **Game Controls**: Intuitive interface for taking turns

### Player Setup Dialog
- **Player Count Selection**: Choose 1-3 players using a spinner
- **Name Entry**: Custom names for each player
- **Input Validation**: Ensures all players have names before starting

### Game Control Panel
- **Current Turn Display**: Shows whose turn it is
- **Draw Card Button**: Large, easy-to-click button for taking turns
- **Game Status**: Current game state and progress
- **Move Log**: Scrollable text area showing all game moves
- **Player Positions**: Live update of each player's position

### Menu System
- **Game Menu**: Start new games or exit
- **Help Menu**: Access game rules and about information

## Game Controls

- **Draw Card**: Click the "Draw Card" button to take your turn
- **New Game**: Use the Game menu to start over
- **Game Rules**: Access help from the Help menu for rule clarification

## Game Rules

- **Color Cards**: Move to the next space of the matching color
- **Double Color Cards**: Move to the second occurrence of the color
- **Special Character Cards**: Move directly to the character's location
- **No Backward Movement**: Players never move backward with character cards
- **Winning**: Reach or pass position 134 to win

## Classes Overview

### GUI Classes

#### `CandyLandGUI`
- Main application window and entry point for the GUI version
- Manages overall game flow and window layout
- Provides menu system for game options and help

#### `PlayerSetupDialog`
- Modal dialog for initial game setup
- Handles player count selection and name entry
- Validates input before starting the game

#### `GameBoardPanel`
- Visual representation of the 134-space game board
- Draws colored spaces in snake-like pattern
- Shows player positions with colored markers
- Highlights special character locations and finish line

#### `GameControlPanel`
- Game control interface with draw card functionality
- Displays current game state and player information
- Provides scrollable game log of all moves
- Shows real-time player position updates

### Core Game Classes

#### `CandyLandGame`
- Main game controller and logic handler
- Manages game flow, player turns, and win conditions
- Provides API for GUI components to interact with game state
- Handles both console and GUI modes

#### `Player`
- Represents a game player with name and board position
- Tracks player movement and position
- Provides string representation for display

#### `Board`
- Represents the 134-space game board
- Manages colored spaces and special character locations
- Calculates movement based on cards drawn
- Determines winning conditions

#### `Card`
- Represents game cards (color or character cards)
- Supports both regular and double color cards
- Handles special character card types

#### `Deck`
- Manages the deck of game cards
- Handles shuffling and card drawing
- Auto-reshuffles when empty

#### `Color`
- Enumeration of board colors: RED, PURPLE, YELLOW, BLUE, ORANGE, GREEN
- Provides color mapping for visual representation

## Testing

The project includes comprehensive unit tests with code coverage reporting:

- `PlayerTest`: Tests player creation, movement, and state management
- `BoardTest`: Tests board functionality, special locations, and win conditions
- `CandyLandGameTest`: Tests core game logic and player management

### Running Tests Locally
```bash
# Run all tests
mvn test

# Run tests with coverage report
mvn clean test jacoco:report

# View coverage report (opens in browser)
open target/site/jacoco/index.html
```

### Code Quality Analysis
```bash
# Run SonarQube analysis locally (requires SONAR_TOKEN environment variable)
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=candy-land-java-game \
  -Dsonar.organization=your-organization \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=$SONAR_TOKEN
```

## Development

### Code Style
- Follow Java naming conventions
- Use clear, descriptive method and variable names
- Include Javadoc comments for public methods
- Maintain separation of concerns between classes

### Contributing
1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Make your changes and add tests
4. Run tests locally: `mvn test`
5. Ensure code quality: Check SonarQube analysis results
6. Commit your changes: `git commit -m "Add your feature"`
7. Push to the branch: `git push origin feature/your-feature`
8. Submit a pull request

### Adding Features
- New game rules can be added to the `Board` class
- Additional card types can be implemented in the `Card` class
- Game variations can be added to `CandyLandGame`
- GUI enhancements can be made to the components in the `gui` package

### Code Quality Standards
The project maintains high code quality through:
- **Unit Testing**: Comprehensive test coverage for all core functionality
- **Static Analysis**: SonarQube analysis for code quality, security, and maintainability
- **Code Coverage**: JaCoCo integration for test coverage reporting
- **Continuous Integration**: Automated testing and analysis on every commit

## License

This project is for educational purposes and implements the classic Candy Land game mechanics.