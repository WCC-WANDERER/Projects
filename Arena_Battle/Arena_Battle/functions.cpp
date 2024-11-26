
#include "functions.h"

void set_texture(sf::Texture& texture, const std::string& texture_name) {
	if (!texture.loadFromFile(texture_name))
		return;
	texture.setSmooth(true);
	return;
}

void set_music(sf::Music& music, const std::string& music_name)
{
	if (!music.openFromFile(music_name))
		return;
	music.setVolume(70);
	music.setLoop(true);
}

//void set_center() {
//	text1.setPosition(rect1.getPosition().x + rect1.getSize().x / 2 - text1.getLocalBounds().width / 2, 
//		rect1.getPosition().y + rect1.getSize().y / 2 - text1.getLocalBounds().height / 2);
//}

void EventHandling(sf::RenderWindow& window, sf::View& view, sf::Sprite& player, sf::Texture& right, sf::Texture& left, sf::Sprite& arena, std::vector<std::unique_ptr<gameTile>>& bushes, bool& battle) {

	//collision(player, obstacles);
	if (battle == false) {
		// gameWorld movement control
		float movement = 2.5f;

		if ((sf::Keyboard::isKeyPressed(sf::Keyboard::Scan::W) || sf::Keyboard::isKeyPressed(sf::Keyboard::Scan::Up)) && player.getGlobalBounds().top > 0 && battle == false) {
			player.move(0.f, -movement);
			for (auto& bush : bushes) {
				if (player.getGlobalBounds().intersects(bush->sprite.getGlobalBounds())) {
					player.move(0.f, movement);
					return;
				}
			}
			if (view.getCenter().y - view.getSize().y / 2 <= 0)
				view.setCenter(view.getCenter().x, view.getSize().y / 2);
			else
				view.move(0.f, -movement);
			window.setView(view);
		}

		if ((sf::Keyboard::isKeyPressed(sf::Keyboard::Scan::S) || sf::Keyboard::isKeyPressed(sf::Keyboard::Scan::Down)) && player.getGlobalBounds().top + player.getGlobalBounds().height < window.getSize().y && battle == false) {
			player.move(0.f, movement);
			for (auto& bush : bushes) {
				if (player.getGlobalBounds().intersects(bush->sprite.getGlobalBounds())) {
					player.move(0.f, -movement);
					return;
				}
			}
			if (view.getCenter().y + view.getSize().y / 2 >= window.getSize().y)
				view.setCenter(view.getCenter().x, window.getSize().y - view.getSize().y / 2);
			else
				view.move(0.f, movement);
			window.setView(view);
		}

		if ((sf::Keyboard::isKeyPressed(sf::Keyboard::Scan::A) || sf::Keyboard::isKeyPressed(sf::Keyboard::Scan::Left)) && player.getGlobalBounds().left > 0 && battle == false) {
			player.move(-movement, 0.f);
			player.setTexture(left);
			for (auto& bush : bushes) {
				if (player.getGlobalBounds().intersects(bush->sprite.getGlobalBounds())) {
					player.move(movement, 0.f);
					return;
				}
			}
			if (view.getCenter().x - view.getSize().x / 2 <= 0)
				view.setCenter(view.getSize().x / 2, view.getCenter().y);
			else
				view.move(-movement, 0.f);
			window.setView(view);
		}

		if ((sf::Keyboard::isKeyPressed(sf::Keyboard::Scan::D) || sf::Keyboard::isKeyPressed(sf::Keyboard::Scan::Right)) && player.getGlobalBounds().left + player.getGlobalBounds().width < window.getSize().x && battle == false) {
			player.move(movement, 0.f);
			player.setTexture(right);
			for (auto& bush : bushes) {
				if (player.getGlobalBounds().intersects(bush->sprite.getGlobalBounds())) {
					player.move(-movement, 0.f);
					return;
				}
			}
			if (view.getCenter().x + view.getSize().x / 2 >= window.getSize().x)
				view.setCenter(window.getSize().x - view.getSize().x / 2, view.getCenter().y);
			else
				view.move(movement, 0.f);
			window.setView(view);
		}

		// Entry to Arena
		if (player.getGlobalBounds().contains(arena.getPosition().x + arena.getGlobalBounds().width / 2 - player.getGlobalBounds().width / 2, arena.getPosition().y + arena.getGlobalBounds().height / 2)) {
			//ArenaBattle();
			//std::cout << "Enter the Arena?\n";
			//std::cout << "Press Y for Yes or Press N for No.\n";
			//std::string temp;
			//std::cin >> temp;
			//if (temp == "Y") {
			battle = true;
			arena.setPosition(2000.f, 1000.f);
			player.setPosition(-2000.f, -1000.f);
			view.setCenter(sf::Vector2f(window.getSize().x * 3 / 8, window.getSize().y * 3 / 8));
			view.setSize(sf::Vector2f(window.getSize().x * 3 / 4, window.getSize().y * 3 / 4));
			window.setView(view);
			//	}
			//	else if (temp == "N") {
			//		player.move(100.f, 0.f);
			//		view.setCenter(view.getCenter().x + 100.f, view.getCenter().y);
			//		window.setView(view);
			//	}
			//	else
			//		std::cerr << "Incorrect input. Please try again\n";
		}
	}
}



//void collision(sf::Sprite& player, std::vector<std::unique_ptr<gameTile>>& obstacles) {
//
//	for (auto& obs : obstacles)
//	{
//		while (player.getGlobalBounds().intersects(obs->sprite.getGlobalBounds())) {
//
//
//			if (player.getGlobalBounds().left + player.getGlobalBounds().width >= obs->sprite.getGlobalBounds().left)
//			{
//				sf::Vector2f spriteposition = player.getPosition();
//				player.move((obs->sprite.getGlobalBounds().left - (player.getGlobalBounds().left + player.getGlobalBounds().width)), 0.f);
//			}
//			if ((player.getGlobalBounds().top + player.getGlobalBounds().height) >= obs->sprite.getGlobalBounds().top)
//			{
//				sf::Vector2f spriteposition = player.getPosition();
//				player.move(0.f, (obs->sprite.getGlobalBounds().top - (player.getGlobalBounds().top + player.getGlobalBounds().height)));
//			}
//			if (player.getGlobalBounds().left <= (obs->sprite.getGlobalBounds().left + obs->sprite.getGlobalBounds().width))
//			{
//				sf::Vector2f spriteposition = player.getPosition();
//
//				player.move(((obs->sprite.getGlobalBounds().left + obs->sprite.getGlobalBounds().width) - player.getGlobalBounds().left), 0.f);
//			}
//			if (player.getGlobalBounds().top <= (obs->sprite.getGlobalBounds().top + obs->sprite.getGlobalBounds().height))
//			{
//				sf::Vector2f spriteposition = player.getPosition();
//				player.move(0.f, ((obs->sprite.getGlobalBounds().top + obs->sprite.getGlobalBounds().height) - player.getGlobalBounds().top));
//			}			
//		}
//	}
//}