# 🧮 Sci-Fi Scientific Calculator

A feature-rich **Scientific Calculator** Android app built with **Kotlin** and **Android Studio**. It offers a clean, modern UI with multiple calculation modes accessible via tabs.

---

## ✨ Features

| Module | Description |
|---|---|
| 🔢 **Standard Calculator** | Full scientific calculator with trigonometric, logarithmic, and power functions |
| 🌀 **Complex Numbers** | Addition, subtraction, multiplication, division of complex numbers |
| 📐 **Matrix Calculator** | 2×2 and 3×3 matrix operations (add, subtract, multiply, determinant, transpose) |
| 📊 **Statistics** | Mean, median, mode, standard deviation, variance, and more |
| ⚖️ **Unit Converter** | Convert between units of length, mass, temperature, speed, and area |
| 📏 **Equation Solver** | Solve linear and quadratic equations step-by-step |
| 📚 **Formula Library** | Browse a curated collection of mathematical & physics formulas |
| 🔣 **Number Base Converter** | Convert numbers between Binary, Octal, Decimal, and Hexadecimal |
| 🕓 **Calculation History** | View and manage past calculations stored locally using Room DB |

---

## 📸 Screenshots

> _Add screenshots here to showcase your app_

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI:** XML Layouts with ViewBinding
- **Navigation:** ViewPager2 + TabLayout
- **Database:** Room (for calculation history)
- **Architecture:** Fragment-based modular structure
- **Build Tool:** Gradle (Kotlin DSL)
- **Min SDK:** 26 (Android 8.0 Oreo)
- **Target SDK:** 35

---

## 📦 Dependencies

```kotlin
// Core
implementation(libs.androidx.core.ktx)
implementation(libs.androidx.appcompat)
implementation(libs.material)
implementation(libs.androidx.constraintlayout)

// UI
implementation(libs.androidx.viewpager2)
implementation(libs.androidx.recyclerview)
implementation(libs.androidx.cardview)

// Architecture
implementation(libs.androidx.lifecycle.viewmodel.ktx)

// Room Database
implementation(libs.androidx.room.runtime)
implementation(libs.androidx.room.ktx)
kapt(libs.androidx.room.compiler)

// Serialization
implementation(libs.gson)
```

---

## 🚀 Getting Started

### Prerequisites

- Android Studio **Hedgehog** or later
- Android SDK **26+**
- JDK **17**

### Clone the Repository

```bash
git clone https://github.com/your-username/ScientificCalculator.git
cd ScientificCalculator
```

### Open in Android Studio

1. Open **Android Studio**
2. Select **File → Open** and choose the cloned project folder
3. Wait for Gradle sync to complete
4. Connect a device or start an emulator
5. Click ▶️ **Run**

---

## 📁 Project Structure

```
ScientificCalculator/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/scientificcalculator/
│           │   ├── MainActivity.kt
│           │   ├── adapter/
│           │   │   ├── HistoryAdapter.kt
│           │   │   └── MainPagerAdapter.kt
│           │   ├── data/
│           │   │   └── HistoryRepository.kt
│           │   ├── fragment/
│           │   │   ├── CalculatorFragment.kt
│           │   │   ├── ComplexFragment.kt
│           │   │   ├── EquationFragment.kt
│           │   │   ├── FormulaFragment.kt
│           │   │   ├── HistoryFragment.kt
│           │   │   ├── MatrixFragment.kt
│           │   │   ├── NumberBaseFragment.kt
│           │   │   ├── StatisticsFragment.kt
│           │   │   └── UnitConverterFragment.kt
│           │   ├── model/
│           │   │   └── Models.kt
│           │   └── util/
│           │       ├── ExpressionEvaluator.kt
│           │       ├── FormulaData.kt
│           │       └── UnitData.kt
│           └── res/
│               ├── drawable/
│               ├── font/
│               ├── layout/
│               ├── menu/
│               └── values/
└── build.gradle.kts
```

---

## ⚙️ Build Variants

| Variant | Description |
|---|---|
| `debug` | For development and testing |
| `release` | Optimized production build (configure signing before release) |

---

## 🤝 Contributing

Contributions are welcome! Feel free to:
- Open an **issue** for bug reports or feature requests
- Submit a **pull request** with improvements

---

## 📄 License

```
MIT License

Copyright (c) 2026

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

> Built with ❤️ using Kotlin & Android Studio
