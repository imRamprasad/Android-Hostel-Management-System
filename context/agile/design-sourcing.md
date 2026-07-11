# Design Sourcing

Honest version of "get designs from the internet": here's what's real,
legitimate, and actually works — versus what isn't possible.

---

## What's NOT realistic

- An AI agent autonomously "browsing the internet" and copying someone else's
  app screens/assets into your repo. Most UI designs, icons, and illustration
  sets are copyrighted — pulling them in verbatim is a legal risk regardless
  of intent, and I won't do it even if asked directly.
- Scraping Dribbble/Behance/Pinterest images and using them as literal
  templates for your screens.

## What IS realistic and free to use

1. **Material Design 3** (Google, free, open spec) — this is the honest
   default for an Android app. Full component specs, color system, typography
   scale: https://m3.material.io
2. **Material Symbols/Icons** (Google, Apache 2.0 license, free for any use):
   https://fonts.google.com/icons
3. **Google Fonts** — free, open-license typefaces: https://fonts.google.com
4. **Figma Community files** explicitly marked free/open-license — browsable
   at figma.com/community, but check each file's license before using it as
   more than inspiration.
5. **Inspiration, not templates** — I can use image search to pull up real
   examples of hostel/PG apps, fintech apps, or Material 3 apps for you to
   look at and describe what you like ("I like this card style, this color
   mood") — then I design an *original* screen inspired by that direction,
   not copied from it.

## How we'll actually do this per screen

1. You tell me the vibe (e.g. "clean, minimal, blue/white, similar to Google
   Pay's simplicity") — or I show you 3-4 reference images via image search
   and you pick a direction.
2. I define a small token set (colors, spacing, type scale) in
   `mobile-ui-registry.md` using Material 3 as the base system.
3. I build the actual Compose screen against those tokens — original code,
   consistent with every other screen via the registry.
4. `/imprint` captures the new component so future screens match it
   automatically.

## For this project specifically

Given it's a PG/hostel management app, a clean Material 3 look (soft
surfaces, one accent color, clear cards for room/fee status) reads as
trustworthy without needing custom illustration work. I'd suggest one accent
color + Material 3 default neutrals to start — tell me if you want to see
reference directions first.
