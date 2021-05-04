<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![License](	https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)](https://opensource.org/licenses/BSD-3-Clause)
[![License](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/en/)
[![LinkedIn][linkedin-shield]][linkedin-url]




<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/PaulCaroline/CIS120_Final_Project">
    <img src="images/logo.png" alt="Logo" width="100" height="100">
  </a>

  <h3 align="center">Super Smash Bros CIS 120</h3>

  <p align="center">
    A university student's open-source rendition of a Nintendo fighting game🎮
    <br />
    <a href="https://github.com/PaulCaroline/CIS120_Final_Project"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/PaulCaroline/CIS120_Final_Project">View Demo</a>
    ·
    <a href="https://github.com/PaulCaroline/CIS120_Final_Project/issues">Report Bug</a>
    ·
    <a href="https://github.com/PaulCaroline/CIS120_Final_Project/issues">Request Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://github.com/PaulCaroline/CIS120_Final_Project)

I submitted this game in the Spring of 2019 as a final project for the class CIS 120: Programming Languages & Techniques at the University of Pennsylvania. It's designed for two players, and features a selection of stages and characters that were among my favorites in <a href="https://en.wikipedia.org/wiki/Super_Smash_Bros._Ultimate">Super Smash Bros Ultimate</a> for the Nintendo Switch.

From a design standpoint, the game aims to combine the sleek, modern landscapes of Smash Ultimate, with the charming aesthetics of 32-bit sprites as one might likely see in classic Gameboy games.

Super Smash CIS 120 became a sort of rainy-day project for me during the summer of 2019, but I haven't added to it for some time. Please feel free to make any contributions and share suggestions about playing and improving the game. 
### Built With

* [JavaSE 1.8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* [JUnit 4](https://junit.org/junit4/)
* [Java Swing](https://docs.oracle.com/javase/tutorial/uiswing/)



<!-- GETTING STARTED -->
## Getting Started

*Download the code to begin playing and/or contributing to the game*.


### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/PaulCaroline/CIS120_Final_Project
   ```

<!-- USAGE EXAMPLES -->
## Usage
### File Structure  
📦src  
 ┣ 📂paul  
 ┃ ┗ 📂smash  
 ┃ ┃ ┣ 📂display  
 ┃ ┃ ┃ ┣ 📜**Game.java**  
 ┃ ┃ ┃ ┣ 📜GameMenu.java  
 ┃ ┃ ┃ ┣ 📜Helper.java  
 ┃ ┃ ┃ ┣ 📜Hud.java  
 ┃ ┃ ┃ ┣ 📜ImageComponent.java  
 ┃ ┃ ┃ ┣ 📜Menu.java  
 ┃ ┃ ┃ ┣ 📜Stage.java  
 ┃ ┃ ┃ ┗ 📜Window.java  
 ┃ ┃ ┣ 📂framework  
 ┃ ┃ ┃ ┣ 📜Animation.java  
 ┃ ┃ ┃ ┣ 📜GameObject.java  
 ┃ ┃ ┃ ┣ 📜KeyboardInput.java  
 ┃ ┃ ┃ ┣ 📜ObjectAction.java  
 ┃ ┃ ┃ ┣ 📜ObjectType.java  
 ┃ ┃ ┃ ┣ 📜PlayerType.java  
 ┃ ┃ ┃ ┣ 📜Spritesheet.java  
 ┃ ┃ ┃ ┗ 📜StageType.java  
 ┃ ┃ ┗ 📂objects  
 ┃ ┃ ┃ ┣ 📜Hitbox.java  
 ┃ ┃ ┃ ┣ 📜Platform.java  
 ┃ ┃ ┃ ┗ 📜Player.java  
 ┗ 📜GameTest.java  

### Running the Game
1. Run the game in a desktop window by executing `Game.java` found in *src/paul/smash/display/*.  
2. Select two characters and a stage using the Game Setup dropdown from the top left of the game menu as shown below:  

[![Product Name Screen Shot][product-menu]](https://github.com/PaulCaroline/CIS120_Final_Project)

### Playing the Game



<!-- ROADMAP -->
## Roadmap
- [x] Define physics properties & metrics for character performance
- [x] Facilitate game window and animation parameters
- [x] Construct classes for GameObjects, including players, hitboxes, ledge platforms and gameplay huds
- [x] Make the hud responseive to display player damage ratings and remaining stocks (lives)
- [x] Create custom spritesheet animations for character movements and attacks 
- [x] Set up gameplay controls to respond to simultaneous keyboard inputs
- [x] Make the game playable by facilitating player damage, removing lives, and restting player positions until 3 lives are lost
- [ ] Consider adding settings to edit the number of stocks at the start of the game
- [ ] Consider making menu tiles responsive to replace Game Setup dropdown
- [ ] Complete Pikachu attack B effect and lightning bolt animation
- [ ] Facilitate player deaths from falling, as well as fall recovery moves
- [ ] Define limits for character jump heights and frequencies, and ensure a Platform GameObject from which to jump is available
- [ ] Implement smash attack functionality and animations
- [ ] Export finished product as a runnable .jar file

See the [open issues](https://github.com/PaulCaroline/CIS120_Final_Project/issues) for a list of proposed features (and known issues).



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the BSD 3-clause License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Paul Caroline  - paulemmit@gmail.com

Project Link: [https://github.com/PaulCaroline/CIS120_Final_Project](https://github.com/PaulCaroline/CIS120_Final_Project)



<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [NO Body the Dragon](https://www.deviantart.com/no-body-the-dragon) (Mario sprite)
* [Goldstud](https://www.spriters-resource.com/submitter/GoldStud/?source=genre) (Ganondorf sprite, *The Legend of Zelda: Twilight Princess*)
* [SemiJuggalo](https://www.deviantart.com/semijuggalo) (Pikachu sprite, *Pokémon Gold and Silver*)
* [Othniel Drew](https://github.com/othneildrew) (Readme Template)






<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/PaulCaroline/CIS120_Final_Project/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/paul-caroline-336800142
[product-screenshot]: images/screenshot.png
[product-menu]: images/menu_screen.png
