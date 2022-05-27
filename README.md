Summer 2022 Software Camp
=========================

Swerve Drive explorations...


Install FRC "wpilib" development tools
--------------------------------------

Go to https://docs.wpilib.org/en/stable .
Follow either the "Zero to robot" steps, or use the side bar link to "Installing Software", "WPILib Installation Guide", "download the latest release of the installer from GitHub". Either way you will eventually reach 
https://github.com/wpilibsuite/allwpilib/releases .

From there, download the software for either Windows or Mac OS, depending
on your type of laptop. At the time of this writing, those were

 * https://github.com/wpilibsuite/allwpilib/releases/download/v2022.4.1/WPILib_Windows64-2022.4.1.iso
 * https://github.com/wpilibsuite/allwpilib/releases/download/v2022.4.1/WPILib_macOS-2022.4.1.dmg

See "WPILib Installation Guide" https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/wpilib-setup.html for details.

Know how to open a terminal window
----------------------------------

On Windows, click the Windows Start button in the lower left,
type "cmd"  in the "Type here to search" field.
"Command Prompt App" should appear. Click it,
and "Command Prompt" window should open.

On a Mac, open Spotlight (magnifying glass in top right corner
or Command+Space. Type "Terminal", double-click it.

Install GIT 
-----------

For Windows, get it from https://git-scm.com/downloads . 

On a Mac, open a Terminal and type `git` followed by enter.
If you already have git, you'll see a long printout starting with `usage: ...`
and you're all set.
If you don't have git, yet, there will be a prompt for
installing it, so just follow along.

Open VS Code
------------

On Windows, there should be a `2022 WPILib VS Code` icon on your desktop.
You can also use a file explorer, navigate to `C:\Users\Public\wpilib\2022\vscode`
and then double-click `Code[.exe]`.

On a Mac, there should be a `WPILib VS Code 2022` icon on your desktop.
You can also use the Finder, navigate to your 'Home' folder and then `wpilib/2022/vscode`
to start `Code[.app]`.

Get a copy of our sources
-------------------------

Open VS Code, then

 * In the View menu, select "Command Palette"
 * Invoke "Git: Clone"
 * Enter https://github.com/team2393/Summer2022
 * When prompted for the location of the code, use a "git" subfolder of your home directory


Additional Resources
--------------------

 * Basics of Java:
   [Free Java Book](https://greenteapress.com/wp/think-java-2e/),
   [Java Book](https://www.amazon.com/dp/0596009208)
 * WPILib: https://docs.wpilib.org/en/stable.
 * Cross-the-Road Electronics, Phoenix tuner and manuals for several CAN devices: https://newsite.ctr-electronics.com/
 * Other resources: https://www.chiefdelphi.com/, https://www.andymark.com/, https://docs.photonvision.org,
   https://trickingrockstothink.com/blog_posts/2019/10/19/tuning_pid.html,
   https://trickingrockstothink.com/blog_posts/2019/10/26/controls_supp_arm.html
