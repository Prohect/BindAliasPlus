DONE (v1.3.0):
- Removed dropAlias from lockCursorBlackList, then removed all lockCursorBlackList logic
- Added Inventory / CreativeModeInventory support for dropAlias via slotClicked + ContainerInput.THROW
- Continuous container drop driven by tick mixin (matches vanilla GLFW key-repeat behavior)
- DropAlias.reapplyToGameKeyMapping() overridden to prevent extra drops on cursor re-lock
