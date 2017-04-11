# WiFi Toggle App

## Usage

### Turn WiFi on
`adb shell am broadcast -a WifiChange -n com.jonathanfinerty.wifitoggle/.ChangeWifiStateReceiver --ez enabled true`

### Turn WiFi off
`adb shell am broadcast -a WifiChange -n com.jonathanfinerty.wifitoggle/.ChangeWifiStateReceiver --ez enabled false`