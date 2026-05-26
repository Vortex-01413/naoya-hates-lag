
# Naoya Hates Lag — Fixed Fabric Build

## What was fixed
- Broken Fabric project structure
- Invalid mixin package declarations
- Missing imports
- Invalid particle injection target
- Duplicate legacy source folders
- Missing mixin json
- Missing Fabric metadata
- Invalid Gradle configuration
- Unsafe entity culling calls
- Broken debug HUD initialization

## Added features
- Debug HUD toggle (F8)
- Lightweight particle limiter
- Frustum-based entity culling
- Stable Fabric 1.20.1 setup
- ModMenu compatibility

## Recommended Mods
- Sodium
- Lithium
- FerriteCore
- ImmediatelyFast

## Recommended JVM
-Xms2G -Xmx3G

## Mobile / Pojav Notes
This build is optimized for lower-end Android devices using PojavLauncher or Zalith.


## New Features Added
- Toggleable settings menu in ModMenu
- Adaptive compatibility layer
- Auto-detection of optimization mods
- Automatic feature disabling when overlap is detected
- External entity culling detection
- External particle optimization detection

## Compatibility Logic
If mods like:
- EntityCulling
- Effective
- ParticleRain

are detected, overlapping optimizations are automatically disabled to avoid:
- double culling
- visual glitches
- render conflicts
- duplicated optimization passes
- FPS instability
