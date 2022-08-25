<script>
import ContactListItem from "./ContactListItem.vue";

export default {
  props: ["contacts"],
  emits: ["refreshContact"],

  components: {
    ContactListItem,
  },
};
</script>
<template>
  <ContactListItem
    v-for="(contact, index) in contacts"
    :contact="contact"
    @refresh-contact="this.$emit('refreshContact')"
  />
</template>
<style></style>
