# AI Contribution

This report summarizes the contributions made by **Gemini 2.5 Pro** to enhance the graphical user interface (GUI) of the Apollo chatbot.  
Work is tracked under two project tags: **A-BetterGui** and **A-AiAssisted**.

---
## Modified Files

- **Amended**
  - `src/main/java/apollo/ui/Message.java`
  - `src/main/java/apollo/Apollo.java`

- **Added**
  - `src/main/resources/css/styles.css`

---

## Key Enhancements

- **External CSS Refactor**  
  Moved all inline `setStyle(...)` calls into a dedicated `styles.css` file, improving readability and maintainability.

- **Modern UI Redesign**
    - Custom dark theme.
    - Updated typography, spacing, and component styling.
    - Icon-based circular **Send** button using `SVGPath`.
    - Rounded input fields with focus effects.
    - **Timestamps** added to message bubbles.

---

## Summary

| Benefit                      | Description                                                                                                   |
|------------------------------|---------------------------------------------------------------------------------------------------------------|
| **Efficiency**               | Delivered ready-to-use Java & CSS code, enabling rapid theme prototyping.                                     |
| **Documentation Shortcut**   | No longer needed referencing JavaFX docs as correct syntax was generated on demand.                           |
| **Code Quality**             | Enforced clean separation of styles, improving code quality.                                                  |
| **Contextual Understanding** | Inferred color palettes and styling from minimal prompts <br/>(e.g., *“use dark theme”*, *“add a timestamp”*) |

---
*Last Updated: September 18, 2025*
