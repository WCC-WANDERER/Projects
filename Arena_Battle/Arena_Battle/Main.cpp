// Main.cpp contains the main function.
// Author: Wing Cheung Chow
// Date: 24-06-2024

#include <iostream>
#include <vector>
#include <string>
#include <filesystem>
#include <ranges>
#include <concepts>
#include <memory>
#include <SFML/Window.hpp>
#include <SFML/Graphics.hpp>
#include <SFML/Audio.hpp>

import Player;
import Entity;
import UserInterface;
import gameMap;
import Arena;
import Chars;
import Monsters;
import Skill;
import Weapon;
import Armour;


int main(int argc, char* argv[]) {

	// Initialize the settings of SFML window
	float window_width = 1600.f;
	float window_height = 800.f;
	sf::RenderWindow window(sf::VideoMode(window_width, window_height), "Arena Battle", sf::Style::Default);
	window.setVerticalSyncEnabled(true);
	window.setKeyRepeatEnabled(false);
	//sf::View view(sf::Vector2f(window_width * 1 / 4, window_height * 1 / 4), sf::Vector2f(window_width * 1 / 2, window_height * 1 / 2));

	// Initialize player object, user interface, world map and arena battle map
	UserInterface UI;
	gameMap world_map(window_width, window_height);

	//Swordsman swd("Swordsman", 30, 50, 150);
	//Archer arc("Archer", 40, 40, 120);
	//Mage mag("Mage", 50, 30, 100);
	Player player;
	Arena battle_map;

	// SFML Main Window and events handling
	while (window.isOpen())
	{
		sf::Event event;
		while (window.pollEvent(event))
		{
			switch (event.type) {

			case sf::Event::Closed:
				if (UI.InitializeTeam(window) && !world_map.getBattleStatus())    // Save player'status only in world map
					player.SaveAll();
				window.close();
				break;

			case sf::Event::KeyPressed:
				if (event.key.scancode == sf::Keyboard::Scan::Escape) {
					if (UI.InitializeTeam(window) && !world_map.getBattleStatus())    // Save player'status only in world map
						player.SaveAll();
					window.close();
				}
				else if (world_map.getBattleStatus())                     // True if the player walk into the arena in world map
					battle_map.EventHandling(window, event, player, world_map);   // Arena battle map event control
				break;

			case sf::Event::MouseButtonPressed:
				UI.EventHandling(window, event, player, world_map, battle_map);    // Main menu event control together with team creation
				break;

			case sf::Event::TextEntered:				
				if (UI.CreateTeam(window))    // Save the characters' name entered by user
					UI.UpdateName(event);				
				break;				
			}
		}

		window.clear(sf::Color::White);   // Background color for team creation

		// Check if Team initialization is done. If yes, enter the world map.
		if (UI.InitializeTeam(window)) {

			if (!world_map.getBattleStatus()) {   // Check if the player enetered the arena. If yes, battle status will become active (true).

				// WorldMap movement
				world_map.EventHandling(window);

				// Play music and draw stuff after entering world map
				world_map.playMusic();
				world_map.draw_all(window);
			}
			else {
				// Play music and draw stuff after entering battle map
				battle_map.playMusic();
				battle_map.draw_all(window);
			}
		}	
		else {
			// Play music and draw stuff before entering world map
			UI.playMusic();
			UI.draw_all(window);
		}	

		// Display
		window.display();
	}

	return 0;

}