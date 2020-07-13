package edu.miu.cs545.group5.onlinemarket.service.impl;

import edu.miu.cs545.group5.onlinemarket.domain.ShoppingCartLine;
import edu.miu.cs545.group5.onlinemarket.repository.ShoppingCartLineRepository;
import edu.miu.cs545.group5.onlinemarket.service.ShoppingCartLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShoppingCartLineServiceImpl implements ShoppingCartLineService {
    @Autowired
    private ShoppingCartLineRepository shoppingCartLineRepository;

    @Override
    public ShoppingCartLine saveShoppingCartLine(ShoppingCartLine shoppingCartLine) {
        return shoppingCartLineRepository.save(shoppingCartLine);
    }
}
