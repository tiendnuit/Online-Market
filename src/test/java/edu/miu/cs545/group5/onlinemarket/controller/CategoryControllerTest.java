package edu.miu.cs545.group5.onlinemarket.controller;

import edu.miu.cs545.group5.onlinemarket.domain.Category;
import edu.miu.cs545.group5.onlinemarket.domain.Seller;
import edu.miu.cs545.group5.onlinemarket.service.CategoryService;
import edu.miu.cs545.group5.onlinemarket.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetCategoryInputForm() {
        // Arrange
        Category category = new Category();
        Seller seller = new Seller();
        when(userService.getLoggedUser()).thenReturn(Optional.of(seller));

        // Act
        String viewName = categoryController.getCategoryInputForm(category, model);

        // Assert
        verify(model, times(1)).addAttribute("seller", seller);
        assertEquals("categoryRegistrationForm", viewName);
    }

    @Test
    public void testSaveCategory_WithBindingErrors() {
        // Arrange
        when(bindingResult.hasErrors()).thenReturn(true);
        Category category = new Category();

        // Act
        String viewName = categoryController.saveCategory(category, bindingResult, model, redirectAttributes);

        // Assert
        assertEquals("categoryRegistrationForm", viewName);
        verify(categoryService, never()).saveCategory(any(Category.class));
    }

    @Test
    public void testSaveCategory_Success() {
        // Arrange
        when(bindingResult.hasErrors()).thenReturn(false);
        Category category = new Category();
        Seller seller = new Seller();
        when(userService.getLoggedUser()).thenReturn(Optional.of(seller));

        // Act
        String viewName = categoryController.saveCategory(category, bindingResult, model, redirectAttributes);

        // Assert
        verify(categoryService, times(1)).saveCategory(category);
        verify(redirectAttributes, times(1)).addFlashAttribute("msg", "Success");
        verify(model, times(1)).addAttribute("seller", seller);
        assertEquals("redirect:/category/categoryInputForm", viewName);
    }
}
