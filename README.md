# Software-Engineering-Project-II 
# Hex Oust - Group 4



## 📌 Overview  
**Hex Oust** is a strategic two-player board game played on a **base-7 hexagonal grid**, where players take turns placing stones following specific rules for non-capturing and capturing placements. The goal is to oust your opponent by completely clearing the board of their stones or capturing all of their stones. No draws are possible in Hex Oust. There is always a winner.



## 👥 Authors  
- ***Ghofran Abourayana***  
- ***Tara Ava Angeline***  
- ***Blessed Fonkoh***  



## 🧩 Implemented Game Rules  
- Players **take turns** placing stones: **Red moves first**, then **Blue**.  
- A player **cannot place a stone adjacent to any of their own stones** on future turns.  
- A red "X" appears when hovering over an **invalid placement**.  
- The game enforces **valid and invalid placements** based on adjacency rules.  
- If a valid move results in a player’s stones creating a larger group, the opponent’s **neighboring stones** to that group are captured and removed.  
- If a player has **no valid moves**, their turn is skipped.  
- The game ends when **one player has no stones left** on the board.



## ✨ Features  
- ✔️ **Board Layout:** Displays a **7-base hexagonal grid**.  
- ✔️ **Turn-Based Gameplay:** Players alternate turns, following game rules.  
- ✔️ **Stone Placement:** Players can place stones in valid positions.  
- ✔️ **Turn Indicator:** Displays which player's turn it is.  
- ✔️ **Invalid Move Indicators:** Shows a **red "X"** on invalid placements and an **error message** if clicked.  
- ✔️ **Capturing Mechanism:** Opponent stones surrounded by a group are automatically removed.  
- ✔️ **Game Over Detection:** Displays the winner and prevents further moves.  
- ✔️ **Restart Option:** Players can reset the game at any time.  



## 🛠 Installation & Running the Game  
### Prerequisites  
- **Java 20 or later**  
- **JavaFX 20 or later**  
- **IDE:** IntelliJ, Eclipse, or VS Code with JavaFX support



### Setup Instructions  
1. Clone this repository:  
   ```sh
   https://github.com/blessedfonkoh/Software-Engineering-Project-II-Group-4.git
2. Open the project in your preferred IDE.
3. Ensure JavaFX is properly configured.
4. Run the main application file.#
   
## Acknowledgements
This project was developed as part of Software Engineering Project II at University College Dublin (UCD).
