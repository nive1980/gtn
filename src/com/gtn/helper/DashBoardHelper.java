package com.gtn.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.gtn.dto.MenuItemDto;
import com.gtn.model.MenuValue;
import com.gtn.model.Product;

public class DashBoardHelper {

	public List<MenuItemDto> prepareLHSDashboardMenu(Collection<MenuValue> menuList, Collection<Product> subscribedProducts){
		
		List<MenuItemDto> menu = new ArrayList<MenuItemDto>(menuList.size());
		List<MenuItemDto> menuTree = new ArrayList<MenuItemDto>();
		Map<Long, String> menuPositions = new HashMap<Long , String>();
		
		for(MenuValue menuLink: menuList){
			MenuItemDto menuItem = new MenuItemDto();
			BeanUtils.copyProperties(menuLink, menuItem);
			
			if("P".equals(menuLink.getLinkType()) && checkSubscribed(subscribedProducts, menuLink)){
				menuItem.setActive("Y");
			}
			else if(menuLink.getProductId()!=null && menuLink.getProductId()==21) {
				System.out.println("is a one time prod");
				menuItem.setActive("Y");
				menuItem.setItemUrl("publication");
					
			}
			else if("P".equals(menuLink.getLinkType()) && !checkSubscribed(subscribedProducts, menuLink)){
				menuItem.setActive("N");
				menuItem.setItemUrl("");
			}else{
				menuItem.setActive("Y");
			}
			
			menu.add(menuItem);
		}
		
		menu.sort((MenuItemDto a, MenuItemDto b) -> {
			if(a.getLevel() < b.getLevel()){
				return -1;
			}else if(a.getLevel() > b.getLevel()){
				return 1;
			}else{
				return 0;
			}
		});
		
		for(MenuItemDto menuItem: menu){
			if(menuItem.getLevel() == 0){
				menuPositions.put(menuItem.getMenuId(), menuTree.size()+"");
				menuTree.add(menuItem);
			}else{
				Long parentId = menuItem.getParentmenuId();			
				String parentPos = menuPositions.get(parentId);				
				String[] parentPosArr = parentPos.split(",");
				
				List<MenuItemDto> temp = menuTree.get(Integer.parseInt(parentPosArr[0])).getSubMenu();
				
				for(int i=1; i<parentPosArr.length; i++){
					temp = temp.get(Integer.parseInt(parentPosArr[i])).getSubMenu();
				}
				
				String childTreePos = parentPos+","+temp.size();
				menuPositions.put(menuItem.getMenuId(), childTreePos);
				
				temp.add(menuItem);
				Collections.sort(temp);
			}
		}

		//addAdminMenu(menuTree);
		
		menuTree.sort((MenuItemDto a, MenuItemDto b) -> {
			if(a.getItemPosition() < b.getItemPosition()){
				return -1;
			}else if(a.getItemPosition() > b.getItemPosition()){
				return 1;
			}else{
				return 0;
			}
		});
		
		return menuTree;
	}
	
	private void addAdminMenu(List<MenuItemDto> menuTree){
		MenuItemDto adminRoot = new MenuItemDto();
		
		adminRoot.setItemPosition(menuTree.get(menuTree.size() - 1).getItemPosition() + 1);
		adminRoot.setDisplayName("Admin");
		adminRoot.setFaIcon("fa-inbox");
		
		List<MenuItemDto> adminChilds = new ArrayList<MenuItemDto>(2);
		
		MenuItemDto scheduledJob = new MenuItemDto();
		scheduledJob.setDisplayName("Schedule Job");
		scheduledJob.setItemUrl("createSearchScheduledJob");
		scheduledJob.setItemPosition(0);
		scheduledJob.setStatus("A");
		scheduledJob.setActive("Y");
		adminChilds.add(scheduledJob);
		
		MenuItemDto globalVariable = new MenuItemDto();
		globalVariable.setDisplayName("Global Variable");
		globalVariable.setItemUrl("sbuParams");
		globalVariable.setItemPosition(1);
		globalVariable.setStatus("A");
		globalVariable.setActive("Y");
		adminChilds.add(globalVariable);
		
		adminRoot.setSubMenu(adminChilds);
		
		menuTree.add(adminRoot);
		
		/// Masters ///
		MenuItemDto masterRoot = new MenuItemDto();
		
		masterRoot.setItemPosition(menuTree.get(menuTree.size() - 1).getItemPosition() + 1);
		masterRoot.setDisplayName("Tables");
		masterRoot.setFaIcon("fa-table");
		
		List<MenuItemDto> masterChilds = new ArrayList<MenuItemDto>(3);
		
		MenuItemDto exporter = new MenuItemDto();
		exporter.setDisplayName("Exporter");
		exporter.setItemUrl("createSearchExporter");
		exporter.setItemPosition(0);
		exporter.setStatus("A");
		exporter.setActive("Y");
		masterChilds.add(exporter);
		
		MenuItemDto consigneee = new MenuItemDto();
		consigneee.setDisplayName("Consigneee");
		consigneee.setItemUrl("createSearchConsignee");
		consigneee.setItemPosition(1);
		consigneee.setStatus("A");
		consigneee.setActive("Y");
		masterChilds.add(consigneee);
		
		MenuItemDto product = new MenuItemDto();
		product.setDisplayName("Product");
		product.setItemUrl("createSearchProduct");
		product.setItemPosition(2);
		product.setStatus("A");
		product.setActive("Y");
		masterChilds.add(product);
		
		masterRoot.setSubMenu(masterChilds);
		
		menuTree.add(masterRoot);
	}

	private boolean checkSubscribed(Collection<Product> subscribedProducts, MenuValue menu){
		
		for(Product product: subscribedProducts){
			if(menu.getProductId() == product.getProductId()){
				return true;
			}
		}
		
		return false;
	}
	
}
