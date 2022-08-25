<script>
export default {
  name: "Button",
  props: ["buttonStyle", "icon", "text", "color"],
};
</script>
<template>
  <button :class="[buttonStyle]">
    <component class="icon" :is="icon"></component>
    <span v-if="text" :class="{ iconpresent: icon }">{{ text }}</span>
  </button>
</template>
<style scoped>
.iconpresent {
  margin-left: 14px;
}

button {
  display: flex;
  justify-items: center;
  align-items: center;
  text-align: center;

  padding: 0.6875rem;
  font-family: "LexenDenca";
  font-size: 14px;

  color: var(--text-primary);
  cursor: pointer;
  border-radius: 8px;
}

.icon {
  width: 20px;
  height: 20px;
}
.primary {
  background: var(--primary-60);
}
.primary:hover {
  background: var(--primary-50);
}

.primary:active {
  background: var(--primary-40);
}

.secondary {
  background: var(--primary-100);
}
.secondary:hover {
  background: var(--primary-90);
}

.secondary:active {
  background: var(--primary-80);
}
.special {
  background: linear-gradient(180deg, rgba(40, 40, 40, 0.7) 0%, #282828 100%),
    #141414;
  border-radius: 1000px;
}
.special:hover {
  background: linear-gradient(
      0deg,
      rgba(255, 255, 255, 0.04),
      rgba(255, 255, 255, 0.04)
    ),
    linear-gradient(180deg, rgba(40, 40, 40, 0.7) 0%, #282828 100%), #141414;
}
.special:active {
  background: linear-gradient(
      0deg,
      rgba(255, 255, 255, 0.04),
      rgba(255, 255, 255, 0.04)
    ),
    linear-gradient(180deg, rgba(40, 40, 40, 0.7) 0%, #282828 100%), #141414;
}
</style>
