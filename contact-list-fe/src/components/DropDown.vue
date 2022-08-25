<script>
import DropDownItem from "./DropDownItem.vue";
import { deleteContactById } from "../api/contacts";
import { useToast } from "vue-toastification";
import Modal from "./Modal.vue";

export default {
  props: ["contact"],
  components: {
    DropDownItem,
    Modal,
  },
  setup() {
    const toast = useToast();
    return { toast };
  },
  methods: {
    async deleteContact(id) {
      await deleteContactById(id)
        .then(() => {
          this.$emit("refreshContact");
          this.toast.success("Contact deleted");
        })
        .catch((error) => {
          this.toast.error(error.response.data.message);
        });
    },
  },
};
</script>
<template>
  <div class="wrapper">
    <div class="dropdown">
      <DropDownItem
        icon="IconSettings"
        text="Edit"
        @click="this.$emit('showModal')"
      />
      <DropDownItem icon="IconFavourite" text="Favourite" />
      <DropDownItem
        icon="IconDelete"
        text="Delete "
        @click="deleteContact(contact.id)"
      />
    </div>
  </div>
</template>
<style scoped>
.wrapper {
  position: relative;
  z-index: 10;
}
.dropdown {
  position: absolute;
  top: 30px;
  left: -40px;
  background: var(--primary-80);
  border-radius: 8px;
  overflow: hidden;
}
</style>
