<script>
export default {
  props: ["icon", "text"],
};
</script>
<template>
  <div class="dropdown-item">
    <component class="icon" :is="icon"></component>{{ text }}
  </div>
</template>
<style scoped>
.dropdown-item {
  display: flex;
  justify-content: left;
  align-items: center;
  text-align: left;
  padding: 0.75rem 1rem;
  min-width: 150px;
  cursor: pointer;
}
.dropdown-item:hover {
  background-color: var(--primary-70);
}
.icon {
  margin-right: 1rem;
  width: 20px;
  height: 20px;
}
</style>
