
export module Player;
import Chars;
import <iostream>;
import <fstream>;
import <sstream>;
import <string>;
import <filesystem>;
//import gameMap;


export class Player {

protected:
	//Swordsman swordsman;
	//Archer archer;
	//Mage mage;
	std::vector<std::shared_ptr<Characters>> chars;
	int current_stage = 0;

public:
	//Player() = default;
	Player() {

		//swordsman = swd;
		//archer = arc;
		//mage = mag;
		chars.push_back(std::make_shared<Swordsman>("Swordsman", 30, 50, 150));
		chars.push_back(std::make_shared<Archer>("Archer", 40, 40, 120));
		chars.push_back(std::make_shared<Mage>("Mage", 50, 30, 100));
	}
	Player(std::vector<std::shared_ptr<Characters>>& chars_update) {

		chars = chars_update;
	}
	~Player() = default;

	auto& getCharList() { return chars; }
	int getStage() { return current_stage; }
	void setStage(int level) { current_stage = level; }
	//Swordsman& getSwordsman() { return this->swordsman; }
	//Archer& getArcher() { return this->archer; }
	//Mage& getMage() { return this->mage; }
	auto& getCharacter(int index) {

		switch (index) {

		case 0:
			return chars[0];

		case 1:
			return chars[1];

		case 2:
			return chars[2];
		}
	}

	void SaveAll() {

		if (!std::filesystem::exists("save"))
			std::filesystem::create_directory("save");

		std::ofstream Save_file("save/LatestSave.txt");
		if (Save_file) {
			Save_file << "Stage" << " " << current_stage << "\n";

			for (auto& c : chars) {

				std::string name = c->getName();
				int atk = c->getAttack();
				int def = c->getDefence();
				int hp = c->getHp();
				Save_file << name << " " << atk << " " << def << " " << hp << "\n";
				std::vector<skill> skill_set = std::move(c->getSkillSet());
				for (auto& skill : skill_set) {
					std::string skill_class = skill.getClass();
					std::string skill_name = skill.getName();
					int skill_atk = skill.getAtk();
					int skill_def = skill.getDef();
					int skill_hp = skill.getHp();
					Save_file << "Skill" << " " << skill_class << " " << skill_name << " " << skill_atk << " " << skill_def << " " << skill_hp << "\n";
				}
				std::string weapon_class = c->getCurrentWeapon().getClass();
				std::string weapon_name = c->getCurrentWeapon().getName();
				int weapon_atk = c->getCurrentWeapon().getAtk();
				Save_file << "Weapon" << " " << weapon_class << " " << weapon_name << " " << weapon_atk << "\n";
				std::string armour_class = c->getCurrentArmour().getClass();
				std::string armour_name = c->getCurrentArmour().getName();
				int armour_def = c->getCurrentArmour().getDef();
				int armour_hp = c->getCurrentArmour().getHp();
				Save_file << "Armour" << " " << armour_class << " " << armour_name << " " << armour_def << " " << armour_hp << "\n";

			}
		}
		else
			std::cerr << "Cannot open the file.\n";
	}

	void LoadAll() {

		if (std::filesystem::exists("save")) {

			std::ifstream player_file("save/LatestSave.txt");
			if (player_file) {

				std::string line;
				std::stringstream ss;
				std::string Stage = "Stage";
				std::string Skill = "Skill";
				std::string Weapon = "Weapon";
				std::string Armour = "Armour";
				std::string name, skill_class, skill_name, skill_name2, weapon_class, weapon_name, weapon_name2, armour_class, armour_name, armour_name2;
				int current_stage, atk, def, hp, skill_atk, skill_def, skill_hp, weapon_atk, armour_def, armour_hp;
				int char_index = 0;

				while (std::getline(player_file, line)) {

					ss.str(line);
					if (line.find(Skill) != std::string::npos) {
						ss >> Skill >> skill_class >> skill_name >> skill_name2 >> skill_atk >> skill_def >> skill_hp;
						skill temp(skill_class, skill_name + " " + skill_name2, skill_atk, skill_def, skill_hp);
						chars[char_index]->addSkill(temp);
					}

					else if (line.find(Weapon) != std::string::npos) {
						ss >> Weapon >> weapon_class >> weapon_name >> weapon_name2 >> weapon_atk;
						weapon temp(weapon_class, weapon_name + " " + weapon_name2, weapon_atk);
						chars[char_index]->addWeapon(temp);
					}

					else if (line.find(Armour) != std::string::npos) {
						ss >> Armour >> armour_class >> armour_name >> armour_name2 >> armour_def >> armour_hp;
						armour temp(armour_class, armour_name + " " + armour_name2, armour_def, armour_hp);
						chars[char_index]->addArmour(temp);
						char_index += 1;
					}

					else if (line.find(Stage) != std::string::npos) {
						ss >> Stage >> current_stage;
						this->setStage(current_stage);
					}

					else {
						ss >> name >> atk >> def >> hp;
						this->chars[char_index]->setName(name);
						this->chars[char_index]->setAttack(atk);
						this->chars[char_index]->setDefence(def);
						this->chars[char_index]->setHealth(hp);
					}

					ss.clear();
				}
			}
			else
				std::cerr << "Cannot open the file.\n";
		}
		else
			std::cerr << "Directory not found.\n";
	}

};


