# Employee Resolution application

This is a simple crud application made for a school assignment.  The objective was to implement a basic android app to preform Crud (Create Read Update Delete) actions on an example database on a local device.

## Important

This app uses local IP addresses and therefore will NOT work if it's not on an emulator on the same device as the database.  If you move the app to an actual mobile device it will not work without extra setup not defined below.

## SETUP

First download and install VirtualBox and Vagrant.
Then download [this](https://github.com/axbjos/phpcrudecrudvagrant) repository, open a terminal, navigate to the dirictory you downloaded it to, and run "vagrant up".  This will setup the virtual machine and the database the app connects to.

Once Vagrant is finished setting up the vm, run "vagrant ssh" to connect to the machine, navigate to /var/www/http/, then copy all 4 files in "utilized php files" to there. If there are any conflicts, replace the existing files.

## RUNNING THE APP

I have so far only run the app by compiling and running through android studio, so that is what I recommend.
