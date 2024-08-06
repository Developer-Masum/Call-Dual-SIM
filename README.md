[![](https://jitpack.io/v/Developer-Masum/Call-Dual-SIM.svg)](https://jitpack.io/#Developer-Masum/Call-Dual-SIM)

# DualSIMCallHandler

DualSIMCallHandler is an Android application designed to handle specified calls in a Dual SIM device. The app allows users to choose which SIM card to use for outgoing calls and handles USSD code interactions efficiently.

## Features

- **Dual SIM Support**: Select between SIM 1 and SIM 2 for outgoing calls.
- **USSD Code Interaction**: Automatically handle USSD code interactions with specified commands.
- **Carrier Name Display**: Show carrier names instead of SIM numbers for better clarity.
- **Accessibility Service**: Utilize accessibility services to automate USSD calls and responses.
- **Material Design UI**: User interface designed with Material Design principles for a modern look and feel.
- **Auto Termination**: Service automatically terminates after completing all commands or if an error occurs.

## Requirements

- **Android Studio**: 4.0 or higher
- **Target SDK**: 34
- **Minimum SDK**: 23 (Android 6.0 Marshmallow)
- **Java**: Development language

## Installation

1. Clone the repository:
    ```bash
   
How to

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

    gradle

Add it in your root build.gradle at the end of repositories:

	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.Developer-Masum:Call-Dual-SIM:Tag'
	}


Step 3. Use Code Main Activity


    private MasumService masumService;
    private String ussdCode = "*124#";
    private int sim = 0;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        // Initialize USSDService with the activity context
        masumService = new MasumService(this);

        // Set up button click listener
        sendButton.setOnClickListener(view -> {
            
            masumService.send(ussdCode,sim);

        });
    }



2. Open the project in Android Studio.
3. Build and run the project on your Android device or emulator.

## Usage

1. Open the app and grant necessary permissions.
2. Select the SIM card you want to use for the outgoing call.
3. Enter the USSD code or phone number.
4. Initiate the call or USSD interaction.
5. The app will automatically handle the USSD responses and terminate the service upon completion.

## Development

### Structure

- **MainActivity.java**: Main activity for user interaction.
- **USSDService.java**: Foreground service to handle USSD interactions.
- **AccessibilityService.java**: Accessibility service to automate USSD responses.
- **SIMUtils.java**: Utility class for SIM-related operations.
- **res/**: Contains UI layouts and resources.

### Contributions

Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or support, please contact [your-email@example.com](mailto:developermasum.help.com).

---

Thank you for using DualSIMCallHandler! We hope this app makes managing calls on your Dual SIM device easier and more efficient.
