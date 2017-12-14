/*
 *  __________________
 *  Qaobee
 *  __________________
 *
 *  Copyright (c) 2015.  Qaobee
 *  All Rights Reserved.
 *
 *  NOTICE: All information contained here is, and remains
 *  the property of Qaobee and its suppliers,
 *  if any. The intellectual and technical concepts contained
 *  here are proprietary to Qaobee and its suppliers and may
 *  be covered by U.S. and Foreign Patents, patents in process,
 *  and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from Qaobee.
 */

//////////////////////////////////////////////////////////
/* 
 * SCRIPT MONGO DB
 * PARAMETRAGE Country SETTINGS
 * V1.0
 * 
 * This script creates documents for collections :
 * - Country
 * 
 * AUTHOR : Jerome
 */
//////////////////////////////////////////////////////////


db.Country.remove({"local" : "fr"});
db.Country.insert({"_id" : "CNTR-4-AF-AFG" , "codeOSCE" : NumberInt(4) , "label" : "Afghanestan" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-8-AL-ALB" , "codeOSCE" : NumberInt(8) , "label" : "Shqipëri" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-12-DZ-DZA" , "codeOSCE" : NumberInt(12) , "label" : "Algérie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-16-AS-ASM" , "codeOSCE" : NumberInt(16) , "label" : "Samoa" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-20-AD-AND" , "codeOSCE" : NumberInt(20) , "label" : "Andorra" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-24-AO-AGO" , "codeOSCE" : NumberInt(24) , "label" : "Angola" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-28-AG-ATG" , "codeOSCE" : NumberInt(28) , "label" : "Antigua-et-Barbuda" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-31-AZ-AZE" , "codeOSCE" : NumberInt(31) , "label" : "Azerbaïdjan" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-32-AR-ARG" , "codeOSCE" : NumberInt(32) , "label" : "Argentine" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-36-AU-AUS" , "codeOSCE" : NumberInt(36) , "label" : "Australie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-40-AT-AUT" , "codeOSCE" : NumberInt(40) , "label" : "Autriche" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-44-BS-BHS" , "codeOSCE" : NumberInt(44) , "label" : "Bahamas" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-48-BH-BHR" , "codeOSCE" : NumberInt(48) , "label" : "Bahreïn" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-50-BD-BGD" , "codeOSCE" : NumberInt(50) , "label" : "Bangladesh" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-51-AM-ARM" , "codeOSCE" : NumberInt(51) , "label" : "Arménie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-52-BB-BRB" , "codeOSCE" : NumberInt(52) , "label" : "Barbade" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-56-BE-BEL" , "codeOSCE" : NumberInt(56) , "label" : "Belgique" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-60-BM-BMU" , "codeOSCE" : NumberInt(60) , "label" : "Bermudes" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-64-BT-BTN" , "codeOSCE" : NumberInt(64) , "label" : "Bhoutan" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-68-BO-BOL" , "codeOSCE" : NumberInt(68) , "label" : "Bolivie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-70-BA-BIH" , "codeOSCE" : NumberInt(70) , "label" : "Bosnie-Herzégovine" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-72-BW-BWA" , "codeOSCE" : NumberInt(72) , "label" : "Botswana" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-76-BR-BRA" , "codeOSCE" : NumberInt(76) , "label" : "Brésil" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-100-BG-BGR" , "codeOSCE" : NumberInt(100) , "label" : "Bulgarie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-104-MM-MMR" , "codeOSCE" : NumberInt(104) , "label" : "Myanmar" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-108-BI-BDI" , "codeOSCE" : NumberInt(108) , "label" : "Burundi" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-112-BY-BLR" , "codeOSCE" : NumberInt(112) , "label" : "Bélarus" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-116-KH-KHM" , "codeOSCE" : NumberInt(116) , "label" : "Cambodge" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-120-CM-CMR" , "codeOSCE" : NumberInt(120) , "label" : "Cameroun" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-124-CA-CAN" , "codeOSCE" : NumberInt(124) , "label" : "Canada" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-132-CV-CPV" , "codeOSCE" : NumberInt(132) , "label" : "Cap-vert" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-136-KY-CYM" , "codeOSCE" : NumberInt(136) , "label" : "Îles Caïmanes" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-140-CF-CAF" , "codeOSCE" : NumberInt(140) , "label" : "République Centrafricaine" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-144-LK-LKA" , "codeOSCE" : NumberInt(144) , "label" : "Sri Lanka" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-148-TD-TCD" , "codeOSCE" : NumberInt(148) , "label" : "Tchad" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-152-CL-CHL" , "codeOSCE" : NumberInt(152) , "label" : "Chili" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-156-CN-CHN" , "codeOSCE" : NumberInt(156) , "label" : "Chine" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-158-TW-TWN" , "codeOSCE" : NumberInt(158) , "label" : "Taïwan" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-170-CO-COL" , "codeOSCE" : NumberInt(170) , "label" : "Colombie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-174-KM-COM" , "codeOSCE" : NumberInt(174) , "label" : "Comores" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-175-YT-MYT" , "codeOSCE" : NumberInt(175) , "label" : "Mayotte" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-178-CG-COG" , "codeOSCE" : NumberInt(178) , "label" : "République du Congo" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-188-CR-CRI" , "codeOSCE" : NumberInt(188) , "label" : "Costa Rica" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-191-HR-HRV" , "codeOSCE" : NumberInt(191) , "label" : "Croatie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-192-CU-CUB" , "codeOSCE" : NumberInt(192) , "label" : "Cuba" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-196-CY-CYP" , "codeOSCE" : NumberInt(196) , "label" : "Chypre" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-203-CZ-CZE" , "codeOSCE" : NumberInt(203) , "label" : "République Tchèque" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-204-BJ-BEN" , "codeOSCE" : NumberInt(204) , "label" : "Bénin" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-208-DK-DNK" , "codeOSCE" : NumberInt(208) , "label" : "Danemark" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-214-DO-DOM" , "codeOSCE" : NumberInt(214) , "label" : "République Dominicaine" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-218-EC-ECU" , "codeOSCE" : NumberInt(218) , "label" : "Équateur" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-222-SV-SLV" , "codeOSCE" : NumberInt(222) , "label" : "El Salvador" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-226-GQ-GNQ" , "codeOSCE" : NumberInt(226) , "label" : "Guinée Équatoriale" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-231-ET-ETH" , "codeOSCE" : NumberInt(231) , "label" : "Éthiopie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-232-ER-ERI" , "codeOSCE" : NumberInt(232) , "label" : "Érythrée" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-233-EE-EST" , "codeOSCE" : NumberInt(233) , "label" : "Estonie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-234-FO-FRO" , "codeOSCE" : NumberInt(234) , "label" : "Îles Féroé" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-238-FK-FLK" , "codeOSCE" : NumberInt(238) , "label" : "Îles (malouines) Falkland" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-242-FJ-FJI" , "codeOSCE" : NumberInt(242) , "label" : "Fidji" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-246-FI-FIN" , "codeOSCE" : NumberInt(246) , "label" : "Finlande" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-254-GF-GUF" , "codeOSCE" : NumberInt(254) , "label" : "Guyane Française" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-258-PF-PYF" , "codeOSCE" : NumberInt(258) , "label" : "Polynésie Française" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-260-TF-ATF" , "codeOSCE" : NumberInt(260) , "label" : "Terres Australes Françaises" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-262-DJ-DJI" , "codeOSCE" : NumberInt(262) , "label" : "Djibouti" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-266-GA-GAB" , "codeOSCE" : NumberInt(266) , "label" : "Gabon" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-268-GE-GEO" , "codeOSCE" : NumberInt(268) , "label" : "Géorgie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-270-GM-GMB" , "codeOSCE" : NumberInt(270) , "label" : "Gambie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-275-PS-PSE" , "codeOSCE" : NumberInt(275) , "label" : "Territoire Palestinien" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-276-DE-DEU" , "codeOSCE" : NumberInt(276) , "label" : "Allemagne" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-288-GH-GHA" , "codeOSCE" : NumberInt(288) , "label" : "Ghana" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-292-GI-GIB" , "codeOSCE" : NumberInt(292) , "label" : "Gibraltar" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-296-KI-KIR" , "codeOSCE" : NumberInt(296) , "label" : "Kiribati" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-300-GR-GRC" , "codeOSCE" : NumberInt(300) , "label" : "Grèce" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-304-GL-GRL" , "codeOSCE" : NumberInt(304) , "label" : "Groenland" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-308-GD-GRD" , "codeOSCE" : NumberInt(308) , "label" : "Grenade" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-312-GP-GLP" , "codeOSCE" : NumberInt(312) , "label" : "Guadeloupe" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-316-GU-GUM" , "codeOSCE" : NumberInt(316) , "label" : "Guam" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-320-GT-GTM" , "codeOSCE" : NumberInt(320) , "label" : "Guatemala" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-324-GN-GIN" , "codeOSCE" : NumberInt(324) , "label" : "Guinée" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-328-GY-GUY" , "codeOSCE" : NumberInt(328) , "label" : "Guyana" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-332-HT-HTI" , "codeOSCE" : NumberInt(332) , "label" : "Haïti" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-340-HN-HND" , "codeOSCE" : NumberInt(340) , "label" : "Honduras" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-344-HK-HKG" , "codeOSCE" : NumberInt(344) , "label" : "Hong-Kong" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-348-HU-HUN" , "codeOSCE" : NumberInt(348) , "label" : "Hongrie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-352-IS-ISL" , "codeOSCE" : NumberInt(352) , "label" : "Islande" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-356-IN-IND" , "codeOSCE" : NumberInt(356) , "label" : "Inde" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-360-ID-IDN" , "codeOSCE" : NumberInt(360) , "label" : "Indonésie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-364-IR-IRN" , "codeOSCE" : NumberInt(364) , "label" : "Iran" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-368-IQ-IRQ" , "codeOSCE" : NumberInt(368) , "label" : "Iraq" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-372-IE-IRL" , "codeOSCE" : NumberInt(372) , "label" : "Irlande" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-376-IL-ISR" , "codeOSCE" : NumberInt(376) , "label" : "Israël" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-380-IT-ITA" , "codeOSCE" : NumberInt(380) , "label" : "Italie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-384-CI-CIV" , "codeOSCE" : NumberInt(384) , "label" : "Côte d'Ivoire" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-388-JM-JAM" , "codeOSCE" : NumberInt(388) , "label" : "Jamaïque" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-392-JP-JPN" , "codeOSCE" : NumberInt(392) , "label" : "Japon" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-398-KZ-KAZ" , "codeOSCE" : NumberInt(398) , "label" : "Kazakhstan" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-400-JO-JOR" , "codeOSCE" : NumberInt(400) , "label" : "Jordanie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-404-KE-KEN" , "codeOSCE" : NumberInt(404) , "label" : "Kenya" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-408-KP-PRK" , "codeOSCE" : NumberInt(408) , "label" : "République Populaire Démocratique de Corée" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-410-KR-KOR" , "codeOSCE" : NumberInt(410) , "label" : "République de Corée" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-414-KW-KWT" , "codeOSCE" : NumberInt(414) , "label" : "Koweït" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-417-KG-KGZ" , "codeOSCE" : NumberInt(417) , "label" : "Kirghizistan" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-422-LB-LBN" , "codeOSCE" : NumberInt(422) , "label" : "Liban" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-426-LS-LSO" , "codeOSCE" : NumberInt(426) , "label" : "Lesotho" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-428-LV-LVA" , "codeOSCE" : NumberInt(428) , "label" : "Lettonie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-430-LR-LBR" , "codeOSCE" : NumberInt(430) , "label" : "Libéria" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-434-LY-LBY" , "codeOSCE" : NumberInt(434) , "label" : "Libye" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-438-LI-LIE" , "codeOSCE" : NumberInt(438) , "label" : "Liechtenstein" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-440-LT-LTU" , "codeOSCE" : NumberInt(440) , "label" : "Lituanie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-442-LU-LUX" , "codeOSCE" : NumberInt(442) , "label" : "Luxembourg" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-446-MO-MAC" , "codeOSCE" : NumberInt(446) , "label" : "Macao" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-450-MG-MDG" , "codeOSCE" : NumberInt(450) , "label" : "Madagascar" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-454-MW-MWI" , "codeOSCE" : NumberInt(454) , "label" : "Malawi" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-458-MY-MYS" , "codeOSCE" : NumberInt(458) , "label" : "Malaisie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-462-MV-MDV" , "codeOSCE" : NumberInt(462) , "label" : "Maldives" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-466-ML-MLI" , "codeOSCE" : NumberInt(466) , "label" : "Mali" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-470-MT-MLT" , "codeOSCE" : NumberInt(470) , "label" : "Malte" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-474-MQ-MTQ" , "codeOSCE" : NumberInt(474) , "label" : "Martinique" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-478-MR-MRT" , "codeOSCE" : NumberInt(478) , "label" : "Mauritanie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-480-MU-MUS" , "codeOSCE" : NumberInt(480) , "label" : "Maurice" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-484-MX-MEX" , "codeOSCE" : NumberInt(484) , "label" : "Mexique" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-492-MC-MCO" , "codeOSCE" : NumberInt(492) , "label" : "Monaco" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-496-MN-MNG" , "codeOSCE" : NumberInt(496) , "label" : "Mongolie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-499-ME-MNE" , "codeOSCE" : NumberInt(499) , "label" : "Moldovie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-498-MD-MDA" , "codeOSCE" : NumberInt(498) , "label" : "Montserrat" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-500-MS-MSR" , "codeOSCE" : NumberInt(500) , "label" : "Maroc" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-504-MA-MAR" , "codeOSCE" : NumberInt(504) , "label" : "Mozambique" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-508-MZ-MOZ" , "codeOSCE" : NumberInt(508) , "label" : "Oman" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-512-OM-OMN" , "codeOSCE" : NumberInt(512) , "label" : "Namibie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-516-NA-NAM" , "codeOSCE" : NumberInt(516) , "label" : "Nauru" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-520-NR-NRU" , "codeOSCE" : NumberInt(520) , "label" : "Népal" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-524-NP-NPL" , "codeOSCE" : NumberInt(524) , "label" : "Pays-Bas" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-540-NC-NCL" , "codeOSCE" : NumberInt(540) , "label" : "Nouvelle-Calédonie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-548-VU-VUT" , "codeOSCE" : NumberInt(548) , "label" : "Vanuatu" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-554-NZ-NZL" , "codeOSCE" : NumberInt(554) , "label" : "Nouvelle-Zélande" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-558-NI-NIC" , "codeOSCE" : NumberInt(558) , "label" : "Nicaragua" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-562-NE-NER" , "codeOSCE" : NumberInt(562) , "label" : "Niger" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-566-NG-NGA" , "codeOSCE" : NumberInt(566) , "label" : "Nigéria" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-578-NO-NOR" , "codeOSCE" : NumberInt(578) , "label" : "Norvège" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-586-PK-PAK" , "codeOSCE" : NumberInt(586) , "label" : "Pakistan" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-591-PA-PAN" , "codeOSCE" : NumberInt(591) , "label" : "Panama" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-598-PG-PNG" , "codeOSCE" : NumberInt(598) , "label" : "Papouasie-Nouvelle-Guinée" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-600-PY-PRY" , "codeOSCE" : NumberInt(600) , "label" : "Paraguay" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-604-PE-PER" , "codeOSCE" : NumberInt(604) , "label" : "Pérou" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-608-PH-PHL" , "codeOSCE" : NumberInt(608) , "label" : "Philippines" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-616-PL-POL" , "codeOSCE" : NumberInt(616) , "label" : "Pologne" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-620-PT-PRT" , "codeOSCE" : NumberInt(620) , "label" : "Portugal" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-624-GW-GNB" , "codeOSCE" : NumberInt(624) , "label" : "Guinée-Bissau" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-630-PR-PRI" , "codeOSCE" : NumberInt(630) , "label" : "Porto Rico" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-634-QA-QAT" , "codeOSCE" : NumberInt(634) , "label" : "Qatar" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-638-RE-REU" , "codeOSCE" : NumberInt(638) , "label" : "Réunion" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-642-RO-ROU" , "codeOSCE" : NumberInt(642) , "label" : "Roumanie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-643-RU-RUS" , "codeOSCE" : NumberInt(643) , "label" : "Russie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-646-RW-RWA" , "codeOSCE" : NumberInt(646) , "label" : "Rwanda" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-654-SH-SHN" , "codeOSCE" : NumberInt(654) , "label" : "Sainte-Hélène" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-660-AI-AIA" , "codeOSCE" : NumberInt(660) , "label" : "Anguilla" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-662-LC-LCA" , "codeOSCE" : NumberInt(662) , "label" : "Sainte-Lucie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-666-PM-SPM" , "codeOSCE" : NumberInt(666) , "label" : "Saint-Pierre-et-Miquelon" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-682-SA-SAU" , "codeOSCE" : NumberInt(682) , "label" : "Arabie Saoudite" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-686-SN-SEN" , "codeOSCE" : NumberInt(686) , "label" : "Sénégal" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-690-SC-SYC" , "codeOSCE" : NumberInt(690) , "label" : "Seychelles" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-694-SL-SLE" , "codeOSCE" : NumberInt(694) , "label" : "Sierra Leone" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-702-SG-SGP" , "codeOSCE" : NumberInt(702) , "label" : "Singapour" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-703-SK-SVK" , "codeOSCE" : NumberInt(703) , "label" : "Slovaquie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-704-VN-VNM" , "codeOSCE" : NumberInt(704) , "label" : "Viet Nam" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-705-SI-SVN" , "codeOSCE" : NumberInt(705) , "label" : "Slovénie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-706-SO-SOM" , "codeOSCE" : NumberInt(706) , "label" : "Somalie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-710-ZA-ZAF" , "codeOSCE" : NumberInt(710) , "label" : "Afrique du Sud" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-716-ZW-ZWE" , "codeOSCE" : NumberInt(716) , "label" : "Zimbabwe" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-724-ES-ESP" , "codeOSCE" : NumberInt(724) , "label" : "Espagne" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-732-EH-ESH" , "codeOSCE" : NumberInt(732) , "label" : "Sahara Occidental" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-736-SD-SDN" , "codeOSCE" : NumberInt(736) , "label" : "Soudan" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-740-SR-SUR" , "codeOSCE" : NumberInt(740) , "label" : "Suriname" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-752-SE-SWE" , "codeOSCE" : NumberInt(752) , "label" : "Suède" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-756-CH-CHE" , "codeOSCE" : NumberInt(756) , "label" : "Suisse" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-760-SY-SYR" , "codeOSCE" : NumberInt(760) , "label" : "Syrie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-762-TJ-TJK" , "codeOSCE" : NumberInt(762) , "label" : "Tadjikistan" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-764-TH-THA" , "codeOSCE" : NumberInt(764) , "label" : "Thaïlande" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-768-TG-TGO" , "codeOSCE" : NumberInt(768) , "label" : "Togo" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-776-TO-TON" , "codeOSCE" : NumberInt(776) , "label" : "Tonga" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-780-TT-TTO" , "codeOSCE" : NumberInt(780) , "label" : "Trinité-et-Tobago" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-784-AE-ARE" , "codeOSCE" : NumberInt(784) , "label" : "Émirats Arabes Unis" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-788-TN-TUN" , "codeOSCE" : NumberInt(788) , "label" : "Tunisie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-792-TR-TUR" , "codeOSCE" : NumberInt(792) , "label" : "Turquie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-795-TM-TKM" , "codeOSCE" : NumberInt(795) , "label" : "Turkménistan" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-800-UG-UGA" , "codeOSCE" : NumberInt(800) , "label" : "Ouganda" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-804-UA-UKR" , "codeOSCE" : NumberInt(804) , "label" : "Ukraine" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-807-MK-MKD" , "codeOSCE" : NumberInt(807) , "label" : "Macédoine" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-818-EG-EGY" , "codeOSCE" : NumberInt(818) , "label" : "Égypte" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-826-GB-GBR" , "codeOSCE" : NumberInt(826) , "label" : "Royaume-Uni" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-834-TZ-TZA" , "codeOSCE" : NumberInt(834) , "label" : "Tanzanie" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-840-US-USA" , "codeOSCE" : NumberInt(840) , "label" : "États-Unis" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-854-BF-BFA" , "codeOSCE" : NumberInt(854) , "label" : "Burkina Faso" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-858-UY-URY" , "codeOSCE" : NumberInt(858) , "label" : "Uruguay" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-860-UZ-UZB" , "codeOSCE" : NumberInt(860) , "label" : "Ouzbékistan" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-862-VE-VEN" , "codeOSCE" : NumberInt(862) , "label" : "Venezuela" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-876-WF-WLF" , "codeOSCE" : NumberInt(876) , "label" : "Wallis et Futuna" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-882-WS-WSM" , "codeOSCE" : NumberInt(882) , "label" : "Samoa" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-887-YE-YEM" , "codeOSCE" : NumberInt(887) , "label" : "Yémen" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-891-CS-SCG" , "codeOSCE" : NumberInt(891) , "label" : "Serbie et Monténégro" , "local" : "fr"});
db.Country.insert({"_id" : "CNTR-894-ZM-ZMB" , "codeOSCE" : NumberInt(894) , "label" : "Zambie" , "local" : "fr"});
