# 📱 Phone Store Management System

A Java-based phone store management system built with:
- MySQL database integration (via JDBC)
- Swing GUI for interactive use
- Core design patterns implemented: Strategy, Observer, State, Singleton, Factory
- Full backend and client-side logic

---

 Features
🔑 User login and signup system
📱 Browse available phones and place orders
💵 Payment system (Cash or Visa) using the Strategy Pattern
🔁 Order status tracking with State Pattern
📬 Request a phone model and receive notifications when available (Observer Pattern)
🛠️ Admin features to manage stock, phones, and orders (Observer Pattern integration)

 Client Features
📝 Register and Login to the system
🔎 Browse available phones with search and filter options
🛒 Place an order for a selected phone
📋 Manage orders:
🔍 View order details
✏️ Modify order (change phone, quantity, payment method)
❌ Cancel an order
💳 Payment options:
Visa payment (redirects to Visa form)
Cash payment (at the store location)
📬 Request a specific phone if it’s unavailable
📢 Receive notification when the requested phone becomes available (Observer Pattern)

 Admin Features
🔑 Login to the admin dashboard
➕ Add new phones to the inventory
🛠️ Update existing phones (price, stock quantity)
🗑️ Delete phones from the store
📬 Manage client phone requests:
Notify waiting clients automatically when a phone is added (Observer Pattern)


✅ Common System Features
🖥️ User-friendly GUI built with Java Swing
🛢️ Database connectivity using JDBC and MySQL
🎯 Design Patterns applied:
📢 Observer Pattern for client notifications
💳 Strategy Pattern for flexible payment methods
🔁 State Pattern for handling order status (Pending, Confirmed, Shipped, Delivered, Cancelled)
🧩 Singleton Pattern for managing the Phone Store as a single instance

