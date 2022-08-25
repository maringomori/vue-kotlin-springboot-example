import httpClient from "./httpClient";

const END_POINT = "/contacts";

const getAllContacts = () => httpClient.get(END_POINT);
const getContactById = (id) => httpClient.get(END_POINT + "/" + id);

const createContact = (name, phoneNumber, email, picture) => {
  let formData = new FormData();
  formData.append("name", name);
  formData.append("phoneNumber", phoneNumber);
  formData.append("email", email);
  formData.append("picture", picture);

  return httpClient.post(END_POINT, formData, {
    headers: { "Content-Type": "multipart/form-data" },
  });
};

const updateContact = (id, name, phoneNumber, email, picture) => {
  let formData = new FormData();
  formData.append("name", name);
  formData.append("phoneNumber", phoneNumber);
  formData.append("email", email);
  formData.append("picture", picture);
  if (picture == undefined) {
    formData.append("delete", true);
  }
  if (picture == "") {
    formData.append("delete", false);
  }

  return httpClient.put(END_POINT + "/" + id, formData, {
    headers: { "Content-Type": "multipart/form-data" },
  });
};
const deleteContactById = (id) => httpClient.delete(END_POINT + "/" + id);

export {
  getAllContacts,
  getContactById,
  createContact,
  updateContact,
  deleteContactById,
};
