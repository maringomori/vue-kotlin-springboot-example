<script before>
export default {
  props: ["url", "width", "height"],
};
</script>

<template>
  <img :src="this.url" alt="avatar" :width="this.width" :height="this.height" />
</template>

<style scoped>
img {
  border-radius: 50%;
}
</style>
