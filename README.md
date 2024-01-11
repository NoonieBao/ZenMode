# NightGuardian
An Xposed module that would halt applications when you ought to be sleeping 😪

# Usage

Warning: Make sure you are able to recover your device when anything unexpected take place!

1. Based on Lsposed, and alternative enhance with Magisk
2. Install the `release.apk` directly
3. Activate NightGuard, and check the target apps in Lsp manager
4. Adding time-spans in NightGuard to set the sleep time



## Enhance：

1. Disable Lsp consistent notification and remove the Lsp manager shortcut.
   1. Check the app-installer of you system in Lsp manager. (When new xposed app installed, Notification will be sent by Lsp)
   2. Check the tell-dial app of you system in Lsp manager, which is different from the incall app. You are able to handle in call but couldont dial if you check. (Some special code may invoke the Lsp manager)
   3. Check any File-Manager that has the root access. (Prevent remove NightGuard in File-Manager)
2. You can install the magisk module in release list, which help install NightGuard as system app. (Un-uninstallable)
   1. Remove Masigk manager, which dose not lose your root and could re-install anytime. (Prevent Magisk module be disabled)
   2. Check any File-Manager that has the root access. (Prevent remove Magisk module in File-Manager)
