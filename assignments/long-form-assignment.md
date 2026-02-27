# Inventory Form Requirements

## Do → Expect (Exact messages / values). Test all paths.

### 1. Login & Session

1. [ ] Valid credentials → Tabs visible, token exists (localStorage.authToken).
2. [ ] Invalid password → Alert text contains: `Invalid credentials. Use: testuser / password123.`
3. [ ] Extra spaces in credentials → Login still works (spaces are automatically trimmed).
4. [ ] Switch the Tab then back → Must login again (forced logout).
5. [ ] Click Logout → Token removed, login screen shown.

### 2. Registration (if used)

1. [ ] Password mismatch → Alert: `Passwords do not match!`
2. [ ] Bad email format → Alert: `Please enter a valid email address`
3. [ ] Password < 8 → Alert: `Password must be at least 8 characters long`
4. [ ] Success → Alert includes `Registration successful; login form shown with email filled`.

### 3. Wizard Step 1 Validation

1. [ ] No device → `Select a device type`
2. [ ] Device chosen, no brand → `Select a brand`
3. [ ] No storage → `Choose storage size`
4. [ ] Quantity 0 / blank → `Quantity must be ≥ 1`
5. [ ] Quantity 11 → `Quantity must be ≤ 10`
6. [ ] Address blank → `Address required`
7. [ ] All corrected + Next → Step 2 shown.
8. [ ] Error summary region appears when any error: `Please correct highlighted fields.`

### 4. Step 1 Pricing Panel

1. [ ] No device+storage → Unit: —, Subtotal: —
2. [ ] Phone 64GB Qty1 → Unit R400.00 Subtotal R400.00
3. [ ] Phone 128GB Qty2 → Unit R480.00 Subtotal R960.00
4. [ ] Laptop 256GB Qty1 → Unit R1,360.00
5. [ ] Clear device → Pricing resets to —

### 5. Step 2 Extras & Pricing

1. [ ] Shipping toggle Standard→Express → Shipping row R25.00 added.
2. [ ] Warranty None→1yr→2yr → Warranty row R0.00 / R49.00 / R89.00.
3. [ ] Formula check example (Phone 128GB Qty2 Express +1yr SAVE10): (R480*2)+R49+R25 = R1,034 → 10% = R103.40 → Total R930.60.

### 6. Discount Codes (Single Item)
 
1. [ ] SAVE10 → Message: `Code SAVE10 applied: -10%`
2. [ ] SAVE20 after SAVE10 → Message updates: `Code SAVE20 applied: -20%`
3. [ ] random → `Invalid code`
4. [ ] Clear input + Apply → discount removed (no message)

### 7. Add To Cart (Multi-Item)

1. [ ] Add valid Step2 item → Cart panel shows (Cart (1 item)).
2. [ ] Add different device → (Cart (2 items)).
3. [ ] First item discounted, second not → Only first shows discount line in its preview text.
4. [ ] Remove one → Item gone; Grand Total = sum remaining totals.
5. [ ] Remove last → Cart panel hidden.
6. [ ] Try Add with missing storage → Errors appear; cart count unchanged.

### 8. Cart Preview Panel (While on Step2)

1. [ ] Appears below pricing once ≥1 item added; remove there updates cart summary instantly.

### 9.  Review Cart Order Flow

1. [ ] Click Review Cart Order → Place Order / Cancel buttons + info box appear.
2. [ ] Click Cancel → Reverts to single Review Cart Order button.
3. [ ] Click Place Order → Success toast shows personalized message: `[UserName], your order was purchased successfully!`
4. [ ] Cart success toast → Shows detailed order info with user name, cart items, and grand total.
5. [ ] Success popup has dismiss button (×) and "View History" button → Manual control, no auto-timeout.
6. [ ] Automatic invoice generation → Creates invoice for cart order with all items and totals.
7. [ ] Double-click Place Order fast → Only one toast.

### 10. Confirm Purchase (Single)

1. [ ] Single purchase → Success toast shows: `[UserName], your order was purchased successfully!`
2. [ ] Toast includes organized sections: Order Details box, Total amount, timestamp.
3. [ ] Success popup has dismiss button (×) in top-right corner → Manually dismissible, no auto-timeout.
4. [ ] Success popup has "View History" button in bottom-right → Expands invoice history and scrolls to section.
5. [ ] Automatic invoice generation → Creates invoice with unique ID, stores in history (last 10).
6. [ ] Cart already has items → Toast only current item; cart list unchanged.

### 11. Invoice Generation & Management

1. [ ] Every purchase → Automatic invoice creation with unique sequential ID.
2. [ ] Invoice includes → Company logo, customer details, itemized list, totals, professional formatting.
3. [ ] Invoice storage → Saves complete data: customerName, customerEmail, deliveryAddress, items, total, htmlContent.
4. [ ] Invoice history panel → Shows last 10 invoices with view/download/delete options.
5. [ ] View invoice → Opens in new tab with HTML format matching design.
6. [ ] Download PDF → Generates professional PDF with jsPDF, includes logo, proper spacing, page boundaries.
7. [ ] PDF Bill To section → Shows customer name, email, and delivery address.
8. [ ] PDF layout → Company logo top-left, company info top-right, proper margins and spacing.
9. [ ] Delete invoice → Removes from history with confirmation, updates count display.
10. [ ] Clear all invoices → Bulk delete option with confirmation dialog.
11. [ ] Invoice counter → Shows "📄 Invoices (X)" with current count in collapsible header.

### 12. Resets & Navigation

1. [ ] Confirm Purchase → Form back to defaults (Step1, cleared).
2. [ ] Place Order (cart) → Cart empty; confirm state reset.
3. [ ] Switch tab (e.g. API) then back → Wizard at Step1, cleared.

### 13. Edge / Negative

1. [ ] Quantity dev-tools set 999 → Error `Quantity must be ≤ 10.`
2. [ ] Remove discounted item → Grand Total drops by its discounted total; no leftover discount.
3. [ ] Attempt Place Order with empty cart (after manual DOM tweak) → No toast (guard works).

### 14. Multi-Device (2 Types) Cart

1. [ ] Add Phone (apple 64GB qty1, standard ship, no warranty, no discount) + Add To Cart → Cart (1).
2. [ ] Configure Laptop (macbook pro 256GB qty1, express ship, 1yr warranty, SAVE10) + Add To Cart → Cart (2).
3. [ ] Both different *deviceType* values (phone, laptop) appear — shows multi-type support.
4. [ ] Only laptop line shows discount + express shipping + warranty costs; phone line stays plain.
5. [ ] Grand Total = phone total + laptop total (add manually, matches to 2 decimals).
6. [ ] Remove laptop → Grand Total becomes just phone's total; no discount lines remain.
7. [ ] Remove phone instead → Discount line for laptop still present; math unchanged for laptop.
8. [ ] Edge: Quickly add phone then immediately laptop without revisiting Step1 for phone again → Earlier phone entry keeps its original color/storage (no bleed from laptop choices).

### 15. Additional Coverage

1. [ ] Device change resets brand: pick phone+apple, switch to tablet → brand cleared, brand select disabled until re-picked.
2. [ ] Preview image vs SVG fallback: laptop/macbook uses laptop image, unknown brand would fallback to SVG (add a temporary brand via dev tools to observe).
3. [ ] Discount removal: apply SAVE10 then clear field + Apply → discount message disappears, pricing recalculates without discount.
4. [ ] Higher discount example: Use SAVE20 on laptop config; manually verify 20% math (show working).
5. [ ] 2yr warranty + express + SAVE10 combined math: verify subtotal + R89 + R25 − 10% lines equal displayed Total.
6. [ ] State isolation: Add discounted laptop to cart, configure new phone (no discount) → existing cart laptop keeps discount line.
7. [ ] Remove from preview list (Step2 small panel) updates main cart summary totals instantly (no stale total).
8. [ ] Single Confirm Purchase with discount: apply SAVE10 then Confirm Purchase (not cart) → toast includes discounted Total (no cart wording).
9. [ ] Double-click Place Order (cart) protection: rapid double click yields one toast and cart empties once.
10. [ ] Tab navigation reset: partially fill form, switch to API tab, return → form back to defaults (Step1, cleared).
11. [ ] Reload clears session: after login press refresh → must login again (token removed).
12. [ ] Aria-live announcements: trigger an error, then fix and submit; ensure screen reader (or inspect DOM) updates alert region.
13. [ ] Color persistence: change color to gold for phone, Add To Cart → new blank form defaults back to black (expected).
14. [ ] Reactive subtotal: change quantity from 1→2→3 on Step1; Subtotal updates each change without blur.
15. [ ] Precision check: choose config producing non-terminating decimal after discount (e.g. phone 128GB qty3 SAVE10) and verify rounded to 2 decimals correctly.
16. [ ] Cart quantity edit: use + / - or direct input to change an item's quantity (1–10); total and Grand Total recalc instantly; buttons disable at bounds.

### 16. Password Change

1. [ ] All fields required → Error: `All fields are required`
2. [ ] Password mismatch → Error: `New passwords do not match`
3. [ ] Password too short → Error: `New password must be at least 6 characters long`
4. [ ] Password visibility toggles → Eye icons work for all 3 password fields.
5. [ ] Successful change → Success message for 3 seconds, form cleared.

### 17. Accessibility Quick

1. [ ] Tab cycles through all actionable elements (login → tabs → form → cart buttons → invoice buttons).
2. [ ] Radio storage selectable by keyboard.
3. [ ] Error text uses role=alert; toast uses role=status.
4. [ ] Success popup dismiss button accessible via keyboard (Enter/Space).
5. [ ] Invoice history panel accessible with aria-labels and proper focus management.

### 18. Invoice Testing Scenarios

1. [ ] Single item purchase → Invoice includes single line item with correct price calculations.
2. [ ] Multi-item cart → Invoice shows all items with individual and total calculations.
3. [ ] Discounted purchase → Invoice reflects discount in both HTML and PDF versions.
4. [ ] Express shipping + warranty → Invoice shows all additional charges correctly.
5. [ ] PDF generation → Verify logo displays, customer info complete, proper formatting.
6. [ ] PDF page boundaries → "Thank you" message and footer stay within margins.
7. [ ] Invoice sequence → Each new purchase gets incrementing invoice number.
8. [ ] History limit → Only last 10 invoices retained, oldest removed automatically.
9. [ ] View invoice → Opens in new tab with professional HTML layout.
10. [ ] Download PDF → File saves with correct name format "Invoice-XXXXX.pdf".
11. [ ] Delete invoice → Removes from history, updates counter, no errors.
12. [ ] Success popup "View History" → Expands history panel and scrolls smoothly.
13. [ ] Empty history → Shows appropriate "No invoices" message.
14. [ ] Company logo → Displays in both HTML invoice and PDF (base64 embedded).
15. [ ] Customer data → Bill To section shows name, email, and delivery address.

### 19. Exact Error Strings (Verify)

1. [ ] `Select a device type`
2. [ ] `Select a brand`
3. [ ] `Choose storage size`
4. [ ] `Quantity must be ≥ 1`
5. [ ] `Quantity must be ≤ 10`
6. [ ] `Address required`
7. [ ] `Invalid code`
8. [ ] `Login alert contains Invalid login credentials`
9. [ ] Error summary: `Please correct highlighted fields.`

#### 20. Pass Criteria: All expectations met, totals math correct to 2 decimals, no console errors.
