package com.example.recycle1.data.model;

public enum WasteType {
  NONE(0, "","",""),
  PLASTIC(1, "Plastique","plas","https://www.actu-environnement.com/images/illustrations/news/12989_une.jpg"),
  GLASS(2, "Verre","ver","https://www.syvadec.fr/photo/art/grande/3009736-4281597.jpg?v=1483613003"),
  FOOD(3, "Alimentaire","fod","https://www.easyrecyclage.com/wp-content/uploads/2019/06/Easyrecyclage_Dechets_Alimentaires.jpg"),
  OTHER(4, "autre","oth","https://previews.123rf.com/images/supernam/supernam1701/supernam170100124/70100270-photo-de-d%C3%A9bris-divers-recyclage.jpg");

  private int id;
  private String name;
  private String type;
  private String pictureUrl;

  WasteType(int id, String name, String type, String pictureUrl) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.pictureUrl = pictureUrl;
  }


  public static Waste getById(int id) {
    Waste waste = new Waste(0,"","","");
    for (WasteType value : values()) {
      if (value.id == id) {

        waste.setId(value.id);
        waste.setName(value.name);
        waste.setType(value.type);
        waste.setPictureUrl(value.pictureUrl);
        return waste;

      }
    }

    return waste;
  }

  @Override public String toString() {
    return name;
  }
}
