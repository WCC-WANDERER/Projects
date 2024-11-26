//#pragma once
//#include<iostream>
//#include<vector>
//#include<string>
//#include<filesystem>
//#include<ranges>
//#include<thread>
//#include<concepts>
//#include "SkillsAndStuff.h"
//
//
//class Characters 
//{
//	unsigned int level, attack, defence, hp;
//	skills skill;
//	weapons weapon;
//	armours armour;
//
//public:
//	Characters() = default;
//	Characters(unsigned int level, unsigned int attack, unsigned int defence, skills skill, weapons weapon, armours armour) {}
//	~Characters() = default;
//
//	unsigned int getlevel() { return level; };
//	unsigned int gethp() { return hp; };
//	unsigned int getattack() { return attack; };
//	unsigned int getdefence() { return defence; };
//	virtual void atk() = 0;
//	virtual void def() = 0;
//};
//
//
//class Warrior : public Characters 
//{
//	std::string name;
//
//public:
//	Warrior() = default;
//	Warrior(std::string name, unsigned int level, unsigned int attack, unsigned int defence, skills skill, weapons weapon, armours armour) : Characters(level, attack, defence, skill, weapon, armour), name(name) {}
//	~Warrior() = default;
//
//	void atk () override {};
//	void def() override {};
//
//};
//
//class Archer : public Characters
//{
//	std::string name;
//
//public:
//	Archer() = default;
//	Archer(std::string name, unsigned int level, unsigned int attack, unsigned int defence, skills skill, weapons weapon, armours armour) : Characters(level, attack, defence, skill, weapon, armour), name(name) {}
//	~Archer() = default;
//
//	void atk() override {};
//	void def() override {};
//
//};
//
//class Magician : public Characters
//{
//	std::string name;
//
//public:
//	Magician() = default;
//	Magician(std::string name, unsigned int level, unsigned int attack, unsigned int defence, skills skill, weapons weapon, armours armour) : Characters(level, attack, defence, skill, weapon, armour), name(name) {}
//	~Magician() = default;
//
//	void atk() override {};
//	void def() override {};
//
//};
