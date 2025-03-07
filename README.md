# Software-Engineering-Project-II 
# Hex Oust - Group 4

## Overview  
Hex Oust is a strategic two-player board game played on a *base-7 hexagonal grid*, where players take turns placing stones following specific rules for non-capturing and capturing placements.The goal is to oust your opponent by completely clearing the board of their stones or capturing all of their stones. Draws cannot occur in HexOust.

## Authors  
- *Ghofran Abourayana*  
- *Tara Ava Angeline*  
- *Blessed Fonkoh*  

## Implemented Game Rules  
- Players *take turns* placing stones: *Red moves first, then **Blue*.  
- A player *cannot place a stone adjacent to any of their own stones* on future turns.  
- A red "X" appears when a player hovers over an *invalid placement*.  
- The game enforces *valid and invalid placements* based on adjacency rules.  
- Additional mechanics such as *capturing moves* will be implemented in later releases.  

## Features Implemented to date 
✔️ *Board Layout:* Displays a *7-base hexagonal grid*.  
✔️ *Turn-Based Gameplay:* Players alternate turns, following game rules.  
✔️ *Stone Placement:* Players can *place stones* in valid positions.  
✔️ *Turn Indicator:* Displays which player's turn it is.  
✔️ *Invalid Move Indicators:* Shows a *red "X"* on invalid placements and *Error message* if clicked.  
✔️ *Restart Game Option:* Players can reset the game at any time.  

## Installation & Running the Game  
### Prerequisites  
- *Java 20 or later*  
- *JavaFX 20 or later*  
- *IDE (IntelliJ, Eclipse, VS Code with JavaFX support)*  

### Setup Instructions  
1. Clone this repository:  
   ```sh
   git clone https://github.com/blessedfonkoh/Software-Engineering-Project-II-Group-4/
