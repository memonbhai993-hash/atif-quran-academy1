# How to Build Your Quran Academy App Using Nevercode (Cloud)

Nevercode is a cloud-based CI/CD service that can build your Android app without needing Android Studio locally.

## Step 1: Create a GitHub Repository

1. Go to **https://github.com** and sign up/login
2. Click **"+"** → **"New repository"**
3. Repository name: `atif-quran-academy`
4. Choose **"Public"** or **"Private"**
5. Click **"Create repository"**

## Step 2: Upload Your Project to GitHub

### Option A: Using Git Command Line
```bash
# Open Command Prompt in your project folder
cd C:/Users/Atif Ahmed/OneDrive/Desktop/quran acc

# Initialize git
git init

# Add all files
git add .

# Commit
git commit -m "Initial commit - Atif Quran Academy App"

# Create repository on GitHub first, then run:
git remote add origin https://github.com/YOUR_USERNAME/atif-quran-academy.git
git branch -M main
git push -u origin main
```

### Option B: Using GitHub Desktop
1. Download **GitHub Desktop** from https://desktop.github.com
2. Install and login with your GitHub account
3. Click **"Add an Existing Repository"**
4. Select folder: `C:/Users/Atif Ahmed/OneDrive/Desktop/quran acc`
5. Click **"Publish repository"**

## Step 3: Sign Up for Nevercode

1. Go to **https://nevercode.io**
2. Click **"Sign Up"**
3. Sign up using **GitHub** (easiest - it will find your repo automatically)
4. Grant necessary permissions

## Step 4: Connect Your Repository

1. In Nevercode dashboard, click **"New Project"**
2. Select **"Android"** as the platform
3. Select your repository: `atif-quran-academy`
4. Nevercode will auto-detect the Gradle configuration
5. Configure build settings:
   - **Gradle version**: 8.4
   - **Android SDK**: 34
   - **Build variant**: `debug`
6. Click **"Create Project"**

## Step 5: Trigger Build

1. Nevercode will automatically start building
2. Watch the build progress in the dashboard
3. Build usually takes **5-10 minutes**
4. If build succeeds, you'll see a green checkmark ✅

## Step 6: Download the APK

1. Click on the **build number** in the dashboard
2. Scroll down to **"Artifacts"**
3. Click **"Download"** next to `app-debug.apk`
4. Transfer this APK to your phone and install it

## Step 7: Install on Phone

1. Transfer `app-debug.apk` to your Android phone
2. Open **File Manager**
3. Find the APK file
4. Tap to install (you may need to enable "Install from unknown sources")
5. Open the app and test!

## Build Status Notifications

In Nevercode settings, you can enable:
- **Email notifications** - Get notified when builds complete
- **Slack integration** - Get notifications in Slack
- **Webhook** - Connect to other services

## Troubleshooting Common Issues

### Build Failed - Missing SDK
Nevercode should auto-install Android SDK. If not:
- Go to **Project Settings** → **Environment**
- Add: `ANDROID_SDK_ROOT=/opt/android-sdk`
- Or ensure SDK is installed in the build configuration

### Gradle Error
Make sure `gradle-wrapper.properties` has correct version:
```
distributionUrl=https\://services.gradle.org/distributions/gradle-8.4-bin.zip
```

### Firebase Error
Ensure `google-services.json` is valid and from your Firebase project.

## Automatic Builds

Configure Nevercode to build automatically:
1. Go to **Project Settings** → **Triggers**
2. Enable **"Build on push"**
3. Every time you push code to GitHub, Nevercode will automatically rebuild

## Pricing

- **Free Tier**: 150 build minutes/month
- **Paid Plans**: Start at $49/month for more builds

## Alternative Cloud Build Services

If Nevercode doesn't work, try:
1. **Bitrise** (bitrise.io) - Free tier available
2. **GitHub Actions** (github.com/features/actions) - Free for public repos
3. **App Center** (appcenter.ms) - Microsoft service, free tier

## Next Steps After Testing

Once you confirm the app works:
1. Create a **release build** (signed APK)
2. Upload to **Google Play Console**
3. Publish your app worldwide!
